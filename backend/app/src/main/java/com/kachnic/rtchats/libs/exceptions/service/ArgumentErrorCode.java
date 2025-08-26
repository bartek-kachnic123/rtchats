package com.kachnic.rtchats.libs.exceptions.service;

public enum ArgumentErrorCode implements ErrorCode {
    MISSING_NULL,
    MISSING_BLANK,
    MAX_VALUE,
    OUT_OF_RANGE;

    private static final String PREFIX = "global.arg.";
    private final String value;

    ArgumentErrorCode() {
        this.value = ErrorCode.format(PREFIX, name());
    }

    @Override
    public String getValue() {
        return value;
    }
}
