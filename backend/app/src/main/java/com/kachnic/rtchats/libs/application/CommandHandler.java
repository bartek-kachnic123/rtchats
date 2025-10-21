package com.kachnic.rtchats.libs.application;

import java.lang.reflect.ParameterizedType;

@FunctionalInterface
public interface CommandHandler<C extends Command> {
    void handle(C command);

    @SuppressWarnings("unchecked")
    default Class<C> getCommandClass() {
        return (Class<C>) ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
