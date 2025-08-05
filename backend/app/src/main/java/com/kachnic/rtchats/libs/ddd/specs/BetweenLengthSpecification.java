package com.kachnic.rtchats.libs.ddd.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.ArgumentOutOfRangeException;

public record BetweenLengthSpecification(int minLength, int maxLength) implements Specification<String> {

    public static BetweenLengthSpecification of(final int minLength, final int maxLength) {
        return new BetweenLengthSpecification(minLength, maxLength);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.ifTrue(isLengthBetween(candidate))
                .thenThrow(() -> new ArgumentOutOfRangeException(
                        "%s must be between %d and %d characters long.".formatted(paramName, minLength, maxLength)));
    }

    private boolean isLengthBetween(final String value) {
        final int length = value.length();
        return length >= minLength && length <= maxLength;
    }
}
