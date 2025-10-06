package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface CommandBus {
    <R, C extends Command<R>> R execute(C command);
}
