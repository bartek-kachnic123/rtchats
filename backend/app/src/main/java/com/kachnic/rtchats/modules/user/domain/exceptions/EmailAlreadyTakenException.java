package com.kachnic.rtchats.modules.user.domain.exceptions;

import java.io.Serial;

public class EmailAlreadyTakenException extends UserException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX = "Email already taken: ";

    public EmailAlreadyTakenException(final String email) {
        super(MESSAGE_PREFIX + email);
    }
}
