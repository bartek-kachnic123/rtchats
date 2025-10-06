package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class MissingCharacterException extends InternalDomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MissingCharacterException(final String message) {
        super(message);
    }
}
