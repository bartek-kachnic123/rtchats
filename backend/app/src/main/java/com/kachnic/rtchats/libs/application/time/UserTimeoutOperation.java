package com.kachnic.rtchats.libs.application.time;

import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserTimeoutOperation implements TimeoutOperation {
    USER_CREATE(400, TimeUnit.MILLISECONDS);

    private final long duration;
    private final TimeUnit unit;

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public TimeUnit getUnit() {
        return unit;
    }
}
