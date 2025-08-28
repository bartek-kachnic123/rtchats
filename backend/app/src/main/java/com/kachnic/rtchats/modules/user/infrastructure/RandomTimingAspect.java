package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.utils.SystemTimer;
import com.kachnic.rtchats.libs.utils.Timer;
import com.kachnic.rtchats.modules.user.application.RandomTimed;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class RandomTimingAspect {

    @Around("@annotation(randomTimed)")
    /* package */ Object enforceRandomTiming(final ProceedingJoinPoint joinPoint, final RandomTimed randomTimed)
            throws Throwable {
        final Timer timer = new SystemTimer();
        timer.start();

        try {
            final Object result = joinPoint.proceed();
            enforceDelay(timer, randomTimed.minMs(), randomTimed.maxMs());
            return result;
        } catch (DomainException e) {
            if (shouldDelay(e, randomTimed.delayOn())) {
                enforceDelay(timer, randomTimed.minMs(), randomTimed.maxMs());
            }
            throw e;
        }
    }

    private void enforceDelay(final Timer timer, final long minMs, final long maxMs) {
        final long targetMs = calculateRandomDelay(minMs, maxMs);
        final long elapsedMs = timer.getElapsedTime(TimeUnit.MILLISECONDS);
        sleepIfNeeded(targetMs - elapsedMs);
    }

    private long calculateRandomDelay(final long minMs, final long maxMs) {
        return (minMs > maxMs) ? 0L : ThreadLocalRandom.current().nextLong(minMs, maxMs + 1);
    }

    private void sleepIfNeeded(final long delayMs) {
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @SafeVarargs
    private boolean shouldDelay(
            final DomainException exc, final Class<? extends DomainException>... delayOnExceptions) {
        final Class<? extends DomainException> excClass = exc.getClass();
        return Arrays.stream(delayOnExceptions).anyMatch(cls -> cls == excClass);
    }
}
