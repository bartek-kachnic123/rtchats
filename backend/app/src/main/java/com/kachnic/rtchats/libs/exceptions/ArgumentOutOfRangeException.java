package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class ArgumentOutOfRangeException extends InternalDomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentOutOfRangeException(final String message) {
        super(message);
    }
}
