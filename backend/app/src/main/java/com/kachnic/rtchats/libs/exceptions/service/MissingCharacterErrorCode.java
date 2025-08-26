package com.kachnic.rtchats.libs.exceptions.service;

public enum MissingCharacterErrorCode implements ErrorCode {
    UPPERCASE,
    LOWERCASE,
    DIGIT,
    SPECIAL;

    private static final String PREFIX = "global.format.missing-character.";
    private final String value;

    MissingCharacterErrorCode() {
        this.value = ErrorCode.format(PREFIX, name());
    }

    @Override
    public String getValue() {
        return value;
    }
}
