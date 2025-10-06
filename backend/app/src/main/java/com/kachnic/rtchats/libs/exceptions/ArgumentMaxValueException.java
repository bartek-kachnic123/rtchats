package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class ArgumentMaxValueException extends InternalDomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentMaxValueException(final String message) {
        super(message);
    }
}
