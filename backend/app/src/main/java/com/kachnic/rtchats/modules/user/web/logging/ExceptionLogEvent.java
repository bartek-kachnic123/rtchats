package com.kachnic.rtchats.modules.user.web.logging;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public record ExceptionLogEvent<T extends Exception>(T exception) implements ResolvableTypeProvider {
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(exception));
    }
}
