package com.kachnic.rtchats.libs.ddd.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.ArgumentOutOfRangeException;

public record MaxLengthSpecification(int maxLength) implements Specification<String> {
    public static MaxLengthSpecification of(final int maxLength) {
        return new MaxLengthSpecification(maxLength);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.ifTrue(exceedsMaxLength(candidate))
                .thenThrow(() -> new ArgumentOutOfRangeException(
                        "%s must be at most %d characters long.".formatted(paramName, maxLength)));
    }

    private boolean exceedsMaxLength(final String value) {
        return value.length() > maxLength;
    }
}
