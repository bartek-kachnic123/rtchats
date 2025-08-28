package com.kachnic.rtchats.libs.specs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.InternalServerException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.libs.utils.SystemTimer;
import com.kachnic.rtchats.libs.utils.Timer;

public final class TimeLimitSpecification<T> implements Specification<T> {

    private final Specification<T> wrappedSpec;
    private final long timeLimitMs;
    private final Timer timer;

    private TimeLimitSpecification(final Specification<T> wrappedSpec, final long timeLimitMs) {
        this.wrappedSpec = wrappedSpec;
        this.timeLimitMs = timeLimitMs;
        this.timer = new SystemTimer();
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

        timer.start();

        try {
            specFuture.get(timeLimitMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException exc) {
            handleInterrupted(exc, specFuture);
        } catch (ExecutionException exc) {
            handleExecution(exc);
        } catch (TimeoutException exc) {
            final long durationMs = timer.getElapsedTime(TimeUnit.MILLISECONDS);
            handleTimeout(specFuture, paramName, wrappedSpec.getClass().getSimpleName(), durationMs);
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
            final Future<Void> specFuture, final String paramName, final String operation, final long durationMs) {
        specFuture.cancel(true);
        throw new TimeLimitExceededException(paramName, operation, durationMs);
    }
}
