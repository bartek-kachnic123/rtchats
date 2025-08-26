package com.kachnic.rtchats.modules.user.domain.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.ErrorCode;

public enum UserErrorCode implements ErrorCode {
    EMAIL_TAKEN("email.email-taken"),
    INVALID_EMAIL("email.invalid-email"),
    INVALID_USERNAME("email.invalid-username");

    private static final String PREFIX = "user.";
    private final String value;

    UserErrorCode(final String suffix) {
        this.value = PREFIX + suffix;
    }

    @Override
    public String getValue() {
        return value;
    }
}
