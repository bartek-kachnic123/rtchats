package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface UseCaseExecutor<R, C extends Command> {
    R execute(C command);
}
