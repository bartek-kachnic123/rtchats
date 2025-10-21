package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.kachnic.rtchats.libs.exceptions.ExceptionBase;
import com.kachnic.rtchats.modules.user.application.RandomTimed;

@Aspect
@Component
class RandomTimingAspect {

    @Around("@annotation(randomTimed)")
    Object enforceRandomTiming(final ProceedingJoinPoint joinPoint, final RandomTimed randomTimed) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            final Object result = joinPoint.proceed();
            enforceDelay(stopWatch, randomTimed.minMillis(), randomTimed.maxMillis());
            return result;
        } catch (ExceptionBase exception) {
            if (shouldDelay(exception, randomTimed.delayOn())) {
                enforceDelay(stopWatch, randomTimed.minMillis(), randomTimed.maxMillis());
            }
            throw exception;
        }
    }

    private void enforceDelay(final StopWatch stopWatch, final long minMillis, final long maxMillis) {
        final long targetMillis = calculateRandomDelay(minMillis, maxMillis);
        final long elapsedMillis = stopWatch.getTotalTimeMillis();
        sleepIfNeeded(targetMillis - elapsedMillis);
    }

    private long calculateRandomDelay(final long minMillis, final long maxMillis) {
        return (minMillis > maxMillis) ? 0L : ThreadLocalRandom.current().nextLong(minMillis, maxMillis + 1);
    }

    private void sleepIfNeeded(final long delayMillis) {
        if (delayMillis > 0) {
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @SafeVarargs
    private boolean shouldDelay(
            final ExceptionBase exception, final Class<? extends ExceptionBase>... delayOnExceptions) {
        final Class<? extends ExceptionBase> exceptionClass = exception.getClass();
        return Arrays.stream(delayOnExceptions).anyMatch(cls -> cls == exceptionClass);
    }
}
