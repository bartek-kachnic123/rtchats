package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public abstract class DomainException extends ExceptionBase implements LocalizableMessage {

    @Serial
    private static final long serialVersionUID = 1L;

    protected DomainException(final String message) {
        super(message);
    }
}
