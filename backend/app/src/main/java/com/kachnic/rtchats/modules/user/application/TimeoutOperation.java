package com.kachnic.rtchats.modules.user.application;

import java.util.concurrent.TimeUnit;

public interface TimeoutOperation {
    long getDuration();

    TimeUnit getUnit();

    String name();
}
