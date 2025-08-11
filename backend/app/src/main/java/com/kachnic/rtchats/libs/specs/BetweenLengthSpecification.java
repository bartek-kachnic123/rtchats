package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.exceptions.ArgumentOutOfRangeException;

public final class BetweenLengthSpecification implements Specification<String> {
    private final int minLength;
    private final int maxLength;

    public BetweenLengthSpecification(final int minLength, final int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public static BetweenLengthSpecification of(final int minLength, final int maxLength) {
        return new BetweenLengthSpecification(minLength, maxLength);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.assertTrue(
                isLengthBetween(candidate), () -> new ArgumentOutOfRangeException(getFormattedMessage(paramName)));
    }

    private boolean isLengthBetween(final String value) {
        final int length = value.length();
        return length >= minLength && length <= maxLength;
    }

    private String getFormattedMessage(final String paramName) {
        return "%s must be between %d and %d characters long.".formatted(paramName, minLength, maxLength);
    }
}
