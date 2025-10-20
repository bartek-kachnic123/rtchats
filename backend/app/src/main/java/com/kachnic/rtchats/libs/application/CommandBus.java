package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface CommandBus {
    <C extends Command> void execute(C command);
}
