package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.exceptions.DomainException;
import java.io.Serial;

class UserException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    /* package */ UserException(final String message) {
        super(message);
    }
}

class InvalidEmailLengthException extends UserException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX = "Max email length: ";

    /* package */ InvalidEmailLengthException(final int maxLength) {
        super(MESSAGE_PREFIX + maxLength);
    }
}

class InvalidEmailFormatException extends UserException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX = "Invalid email format: ";

    /* package */ InvalidEmailFormatException(final String email) {
        super(MESSAGE_PREFIX + email);
    }
}
