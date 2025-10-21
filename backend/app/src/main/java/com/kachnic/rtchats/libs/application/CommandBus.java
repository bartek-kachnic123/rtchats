package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface CommandBus {
    void execute(Command command);
}
