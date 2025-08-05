package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class ArgumentInvalidFormatException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX = "Invalid format for argument: ";

    public ArgumentInvalidFormatException(final String parameterName, final String invalidValue) {
        super(MESSAGE_PREFIX + parameterName + " = " + invalidValue);
    }
}
