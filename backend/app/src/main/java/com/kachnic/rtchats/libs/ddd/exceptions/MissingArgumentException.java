package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class MissingArgumentException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX = "Missing required arg: ";

    public MissingArgumentException(final String argName) {
        super(MESSAGE_PREFIX + argName);
    }
}
