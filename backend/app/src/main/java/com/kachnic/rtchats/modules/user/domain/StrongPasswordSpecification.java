package com.kachnic.rtchats.modules.user.domain;

import com.kachnic.rtchats.libs.exceptions.service.MissingCharacterErrorCode;
import com.kachnic.rtchats.libs.specs.*;
import java.util.regex.Pattern;

final class StrongPasswordSpecification {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 255;
    private static final Pattern SPECIAL = Pattern.compile("[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~]");

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(BetweenLengthSpecification.of(MIN_LENGTH, MAX_LENGTH))
            .and(ContainsSpecification.uppercase())
            .and(ContainsSpecification.lowercase())
            .and(ContainsSpecification.digit())
            .and(ContainsSpecification.of(SPECIAL, MissingCharacterErrorCode.SPECIAL));

    private StrongPasswordSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
