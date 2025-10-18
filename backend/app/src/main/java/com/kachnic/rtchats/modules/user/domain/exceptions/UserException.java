package com.kachnic.rtchats.modules.user.domain.exceptions;

import java.io.Serial;

import com.kachnic.rtchats.libs.exceptions.DomainException;

abstract class UserException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    UserException(final String message) {
        super(message);
    }
}
