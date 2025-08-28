package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidator;
import com.kachnic.rtchats.libs.exceptions.ArgumentMaxValueException;

public final class MaxLengthSpecification implements Specification<String> {

    private final int maxLength;

    private MaxLengthSpecification(final int maxLength) {
        this.maxLength = maxLength;
    }

    public static MaxLengthSpecification of(final int maxLength) {
        return new MaxLengthSpecification(maxLength);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        final int candidateLength = candidate.length();
        DomainValidator.assertTrue(
                isWithinMaxLength(candidateLength),
                () -> new ArgumentMaxValueException(paramName, candidateLength, maxLength));
    }

    private boolean isWithinMaxLength(final int length) {
        return length <= maxLength;
    }
}
