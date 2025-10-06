package com.kachnic.rtchats.libs.application;

public interface LogEventBus {
    <T> void publish(T event);
}
