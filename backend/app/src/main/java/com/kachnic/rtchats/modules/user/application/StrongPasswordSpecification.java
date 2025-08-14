package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.libs.specs.*;
import java.util.regex.Pattern;

final class StrongPasswordSpecification {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 256;

    private static final Pattern UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("\\d");
    private static final Pattern SPECIAL = Pattern.compile("[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~]");

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(BetweenLengthSpecification.of(MIN_LENGTH, MAX_LENGTH))
            .and(ContainsSpecification.of(UPPERCASE))
            .and(ContainsSpecification.of(LOWERCASE))
            .and(ContainsSpecification.of(DIGIT))
            .and(ContainsSpecification.of(SPECIAL));

    private StrongPasswordSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
