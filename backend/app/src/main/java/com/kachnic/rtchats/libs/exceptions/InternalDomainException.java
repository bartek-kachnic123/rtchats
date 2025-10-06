package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

import com.kachnic.rtchats.libs.exceptions.codes.OperationErrorCode;

public class InternalDomainException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalDomainException(final String message) {
        super(message, OperationErrorCode.INTERNAL_SERVER);
    }

    public InternalDomainException(final Throwable cause) {
        super(OperationErrorCode.INTERNAL_SERVER, cause);
    }
}
