package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.exceptions.MissingCharacterException;
import com.kachnic.rtchats.libs.exceptions.service.ErrorCode;
import com.kachnic.rtchats.libs.exceptions.service.MissingCharacterErrorCode;
import java.util.regex.Pattern;

public final class ContainsSpecification implements Specification<String> {
    private final Pattern pattern;
    private final ErrorCode errorCode;

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");

    private ContainsSpecification(final Pattern pattern, final ErrorCode errorCode) {
        this.pattern = pattern;
        this.errorCode = errorCode;
    }

    public static ContainsSpecification of(final Pattern pattern, final ErrorCode errorCode) {
        return new ContainsSpecification(pattern, errorCode);
    }

    public static ContainsSpecification uppercase() {
        return new ContainsSpecification(UPPERCASE_PATTERN, MissingCharacterErrorCode.UPPERCASE);
    }

    public static ContainsSpecification lowercase() {
        return new ContainsSpecification(LOWERCASE_PATTERN, MissingCharacterErrorCode.LOWERCASE);
    }

    public static ContainsSpecification digit() {
        return new ContainsSpecification(DIGIT_PATTERN, MissingCharacterErrorCode.DIGIT);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.assertTrue(
                containsPattern(candidate), () -> new MissingCharacterException(errorCode, paramName, candidate));
    }

    private boolean containsPattern(final String value) {
        return pattern.matcher(value).find();
    }
}
