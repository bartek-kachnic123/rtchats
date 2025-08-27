package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.OperationErrorCode;
import java.io.Serial;

public class InternalServerException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalServerException(final Throwable cause) {
        super(OperationErrorCode.INTERNAL_SERVER, cause);
    }
}
