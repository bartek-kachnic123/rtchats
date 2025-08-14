package com.kachnic.rtchats.libs.ddd;

@FunctionalInterface
public interface UseCaseExecutor<R, C extends Command<?>> {
    R execute(C command);
}
