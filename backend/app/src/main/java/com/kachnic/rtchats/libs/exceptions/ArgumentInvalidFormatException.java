package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class ArgumentInvalidFormatException extends InternalDomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentInvalidFormatException(final String message) {
        super(message);
    }
}
