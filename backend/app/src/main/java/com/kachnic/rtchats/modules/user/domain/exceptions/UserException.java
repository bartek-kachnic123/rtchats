package com.kachnic.rtchats.modules.user.domain.exceptions;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import java.io.Serial;

class UserException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    /* package */ UserException(final String message) {
        super(message);
    }
}
