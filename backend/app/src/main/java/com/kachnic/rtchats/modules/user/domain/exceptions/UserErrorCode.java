package com.kachnic.rtchats.modules.user.domain.exceptions;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;

public enum UserErrorCode implements ErrorCode {
    EMAIL_TAKEN;

    private static final String PREFIX = "user.";
    private final String value;

    UserErrorCode() {
        this.value = ErrorCode.format(PREFIX, name());
    }

    @Override
    public String getValue() {
        return value;
    }
}
