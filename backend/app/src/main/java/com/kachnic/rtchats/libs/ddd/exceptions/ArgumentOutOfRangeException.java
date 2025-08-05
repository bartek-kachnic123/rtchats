package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class ArgumentOutOfRangeException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentOutOfRangeException(final String message) {
        super(message);
    }
}
