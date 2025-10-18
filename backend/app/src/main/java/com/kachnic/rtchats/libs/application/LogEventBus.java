package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface LogEventBus {
    <T> void publish(T event);
}
