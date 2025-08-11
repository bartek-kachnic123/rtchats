package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class InternalServerException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MSG = "Internal Server Error";

    public InternalServerException(final Throwable cause) {
        super(MSG, cause);
    }
}
