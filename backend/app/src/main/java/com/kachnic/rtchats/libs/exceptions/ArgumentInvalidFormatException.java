package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class ArgumentInvalidFormatException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MSG_PREFIX = "Invalid format for argument: ";

    public ArgumentInvalidFormatException(final String parameterName, final String invalidValue) {
        super(MSG_PREFIX + parameterName + " = " + invalidValue);
    }
}
