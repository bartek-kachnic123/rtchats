package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class MissingArgumentException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    public MissingArgumentException(final String message) {
        super(message);
    }
}
