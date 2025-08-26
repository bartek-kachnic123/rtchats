package com.kachnic.rtchats.libs.exceptions.service;

public enum OperationErrorCode implements ErrorCode {
    INTERNAL_SERVER,
    TIME_EXCEEDED,
    NO_SUCH_MESSAGE;

    private static final String PREFIX = "global.operation.";
    private final String value;

    OperationErrorCode() {
        this.value = ErrorCode.format(PREFIX, name());
    }

    @Override
    public String getValue() {
        return value;
    }
}
