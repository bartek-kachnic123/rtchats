package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;
import java.util.List;

public abstract class ExceptionBase extends RuntimeException implements LocalizableMessage {

    @Serial
    private static final long serialVersionUID = 1L;

    protected ExceptionBase(final String message) {
        super(message);
    }

    protected ExceptionBase(final Throwable cause) {
        super(cause);
    }

    protected ExceptionBase(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public List<Object> getArgs() {
        return List.of();
    }
}
