package com.kachnic.rtchats.libs.utils;

import java.util.concurrent.TimeUnit;

public interface Timer {
    void start();

    long getElapsedTime(TimeUnit unit);
}
