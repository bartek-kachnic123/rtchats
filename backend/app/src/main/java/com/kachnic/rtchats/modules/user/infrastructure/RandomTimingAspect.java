package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RandomTimingAspect {

    @Around("@annotation(randomTimed)")
    public Object enforceRandomTiming(final ProceedingJoinPoint joinPoint, final RandomTimed randomTimed)
            throws Throwable {
        final long start = startTime();
        try {
            final Object result = joinPoint.proceed();
            enforceDelay(start, randomTimed.minMs(), randomTimed.maxMs());
            return result;
        } catch (DomainException e) {
            if (shouldDelay(e, randomTimed.delayOn())) {
                enforceDelay(start, randomTimed.minMs(), randomTimed.maxMs());
            }
            throw e;
        }
    }

    private long startTime() {
        return System.nanoTime();
    }

    private void enforceDelay(final long startNano, final int minMs, final int maxMs) {
        final long targetMs = calculateRandomDelay(minMs, maxMs);
        final long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNano);
        sleepIfNeeded(targetMs - elapsedMs);
    }

    private long calculateRandomDelay(final int minMs, final int maxMs) {
        return minMs + ThreadLocalRandom.current().nextInt(maxMs - minMs + 1);
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
