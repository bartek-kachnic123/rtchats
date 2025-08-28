package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

import com.kachnic.rtchats.libs.exceptions.codes.OperationErrorCode;

public class InternalServerException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalServerException(final Throwable cause) {
        super(OperationErrorCode.INTERNAL_SERVER, cause);
    }
}
