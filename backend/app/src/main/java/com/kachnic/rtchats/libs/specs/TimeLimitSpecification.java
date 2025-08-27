package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.InternalServerException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import java.util.concurrent.*;

public final class TimeLimitSpecification<T> implements Specification<T> {

    private final Specification<T> wrappedSpec;
    private final long timeLimitMs;

    private TimeLimitSpecification(final Specification<T> wrappedSpec, final long timeLimitMs) {
        this.wrappedSpec = wrappedSpec;
        this.timeLimitMs = timeLimitMs;
    }

    public static <T> Specification<T> of(final Specification<T> wrappedSpec, final long timeLimitMs) {
        return new TimeLimitSpecification<>(wrappedSpec, timeLimitMs);
    }

    @Override
    public void check(final T candidate, final String paramName) {
        @SuppressWarnings("PMD.CloseResource")
        final ExecutorService commonPool = ForkJoinPool.commonPool();

        final Future<Void> specFuture = commonPool.submit(() -> {
            wrappedSpec.check(candidate, paramName);
            return null;
        });

        final long startTime = System.nanoTime();

        try {
            specFuture.get(timeLimitMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException exc) {
            handleInterrupted(exc, specFuture);
        } catch (ExecutionException exc) {
            handleExecution(exc);
        } catch (TimeoutException exc) {
            handleTimeout(specFuture, paramName, wrappedSpec.getClass().getSimpleName(), startTime);
        }
    }

    private void handleInterrupted(final InterruptedException exc, final Future<Void> specFuture) {
        Thread.currentThread().interrupt();
        specFuture.cancel(true);
        throw new InternalServerException(exc);
    }

    private void handleExecution(final ExecutionException exc) {
        final Throwable cause = exc.getCause();
        if (cause instanceof DomainException domainException) {
            throw domainException;
        }
        throw new InternalServerException(cause);
    }

    private void handleTimeout(
            final Future<Void> specFuture, final String paramName, final String operation, final long startTime) {
        specFuture.cancel(true);
        final long durationMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        throw new TimeLimitExceededException(paramName, operation, durationMs);
    }
}
