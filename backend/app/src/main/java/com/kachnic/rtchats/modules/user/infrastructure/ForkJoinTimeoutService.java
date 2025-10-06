package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.InternalDomainException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.libs.utils.SystemTimer;
import com.kachnic.rtchats.libs.utils.Timer;
import com.kachnic.rtchats.modules.user.application.TimeoutOperation;
import com.kachnic.rtchats.modules.user.application.TimeoutService;

@Service
final class ForkJoinTimeoutService implements TimeoutService {

    private final ExecutorService executor = ForkJoinPool.commonPool();

    @Override
    public <T> T executeWithTimeout(final Callable<T> task, final TimeoutOperation operation) {
        final Timer timer = new SystemTimer();
        final Future<T> future = executor.submit(task);

        timer.start();

        try {
            return future.get(operation.getDuration(), operation.getUnit());
        } catch (InterruptedException exc) {
            throw handleInterruptedException(future, exc);
        } catch (ExecutionException exc) {
            throw handleExecutionException(exc);
        } catch (TimeoutException exc) {
            final long durationMs = timer.getElapsedTime(TimeUnit.MILLISECONDS);
            throw handleTimeoutException(future, operation.name(), durationMs, exc);
        }
    }

    private InternalDomainException handleInterruptedException(final Future<?> future, final InterruptedException exc) {
        Thread.currentThread().interrupt();
        future.cancel(true);
        return new InternalDomainException(exc);
    }

    private DomainException handleExecutionException(final ExecutionException exc) {
        final Throwable cause = exc.getCause();
        return (cause instanceof DomainException) ? (DomainException) cause : new InternalDomainException(cause);
    }

    private TimeLimitExceededException handleTimeoutException(
            final Future<?> future, final String operation, final long durationMs, final Throwable cause) {
        future.cancel(true);
        return new TimeLimitExceededException(operation, durationMs, cause);
    }
}
