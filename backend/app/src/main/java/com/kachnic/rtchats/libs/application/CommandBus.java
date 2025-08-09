package com.kachnic.rtchats.libs.application;

import com.kachnic.rtchats.libs.ddd.Command;

@FunctionalInterface
public interface CommandBus {
    <R, C extends Command<R>> R execute(C command);
}
