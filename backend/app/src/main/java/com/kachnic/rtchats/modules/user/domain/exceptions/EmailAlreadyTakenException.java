package com.kachnic.rtchats.modules.user.domain.exceptions;

import java.io.Serial;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;

public class EmailAlreadyTakenException extends UserException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final ErrorCode CODE = UserErrorCode.EMAIL_TAKEN;

    public EmailAlreadyTakenException(final String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return CODE.getValue();
    }
}
