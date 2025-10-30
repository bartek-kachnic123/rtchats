package com.kachnic.rtchats.libs.application.time;

import java.util.concurrent.TimeUnit;

public interface TimeoutOperation {
    long getDuration();

    TimeUnit getUnit();

    String name();
}
