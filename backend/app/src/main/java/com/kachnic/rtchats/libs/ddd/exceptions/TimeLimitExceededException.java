package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class TimeLimitExceededException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TimeLimitExceededException(final String message) {
        super(message);
    }
}
