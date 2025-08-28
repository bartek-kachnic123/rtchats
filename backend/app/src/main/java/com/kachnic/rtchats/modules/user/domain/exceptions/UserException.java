package com.kachnic.rtchats.modules.user.domain.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;

class UserException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    /* package */ UserException(final ErrorCode code, final List<Serializable> args) {
        super(code, args);
    }
}
