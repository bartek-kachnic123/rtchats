package com.kachnic.rtchats.libs.utils;

import java.util.concurrent.TimeUnit;

public class SystemTimer implements Timer {

    private long startTime;

    @Override
    public void start() {
        startTime = System.nanoTime();
    }

    @Override
    public long getElapsedTime(final TimeUnit unit) {
        final long durationNs = System.nanoTime() - startTime;
        return unit.convert(durationNs, TimeUnit.NANOSECONDS);
    }
}
