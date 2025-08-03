package com.kachnic.rtchats.libs.ddd.exceptions;

import java.io.Serial;

public class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DomainException(final String message) {
        super(message);
    }
}
