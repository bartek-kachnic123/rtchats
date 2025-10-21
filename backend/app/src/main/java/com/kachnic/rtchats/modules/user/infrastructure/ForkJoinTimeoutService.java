package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.kachnic.rtchats.libs.exceptions.ExceptionBase;
import com.kachnic.rtchats.libs.exceptions.InternalException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.modules.user.application.TimeoutOperation;
import com.kachnic.rtchats.modules.user.application.TimeoutService;

@Service
final class ForkJoinTimeoutService implements TimeoutService {

    private final ExecutorService executor = ForkJoinPool.commonPool();

    @Override
    public <T> T executeWithTimeout(final Callable<T> task, final TimeoutOperation operation) {
        final StopWatch stopWatch = new StopWatch();
        final Future<T> future = executor.submit(task);
        stopWatch.start();
        try {
            return future.get(operation.getDuration(), operation.getUnit());
        } catch (InterruptedException exc) {
            throw handleInterruptedException(future, exc);
        } catch (ExecutionException exc) {
            throw handleExecutionException(exc);
        } catch (TimeoutException exc) {
            final long durationMillis = stopWatch.getTotalTimeMillis();
            throw handleTimeoutException(future, operation.name(), durationMillis, exc);
        }
    }

    private InternalException handleInterruptedException(final Future<?> future, final InterruptedException exc) {
        Thread.currentThread().interrupt();
        future.cancel(true);
        return new InternalException(exc);
    }

    private ExceptionBase handleExecutionException(final ExecutionException exc) {
        final Throwable cause = exc.getCause();
        return (cause instanceof ExceptionBase) ? (ExceptionBase) cause : new InternalException(cause);
    }

    private TimeLimitExceededException handleTimeoutException(
            final Future<?> future, final String operation, final long durationMillis, final Throwable cause) {
        future.cancel(true);
        final String message = String.format("%s took %d", operation, durationMillis);
        return new TimeLimitExceededException(message, cause);
    }
}
