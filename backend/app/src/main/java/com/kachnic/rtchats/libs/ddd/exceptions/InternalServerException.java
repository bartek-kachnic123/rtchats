package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class InternalServerException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "Internal Server Error";

    public InternalServerException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
