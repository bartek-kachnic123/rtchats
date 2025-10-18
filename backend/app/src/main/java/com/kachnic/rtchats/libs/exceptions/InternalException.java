package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;
import com.kachnic.rtchats.libs.exceptions.codes.OperationErrorCode;

public class InternalException extends ExceptionBase {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final ErrorCode CODE = OperationErrorCode.INTERNAL_SERVER;

    public InternalException(final String message) {
        super(message);
    }

    public InternalException(final Throwable cause) {
        super(cause);
    }

    public InternalException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return CODE.getValue();
    }
}
