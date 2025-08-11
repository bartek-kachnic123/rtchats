package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import java.util.regex.Pattern;

public final class MatchesFormatSpecification implements Specification<String> {
    private final Pattern pattern;

    private MatchesFormatSpecification(final Pattern pattern) {
        this.pattern = pattern;
    }

    public static MatchesFormatSpecification of(final Pattern pattern) {
        return new MatchesFormatSpecification(pattern);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.assertTrue(
                isValidFormat(candidate), () -> new ArgumentInvalidFormatException(paramName, candidate));
    }

    private boolean isValidFormat(final String value) {
        final CharSequence sequence = new InterruptibleCharSequence(value);
        return pattern.matcher(sequence).matches();
    }

    private record InterruptibleCharSequence(CharSequence inner) implements CharSequence {
        private static final String TIMEOUT_MESSAGE =
                "Regex evaluation was interrupted due to task timeout or cancellation";

        @Override
        public int length() {
            return inner.length();
        }

        @Override
        public char charAt(final int index) {
            if (Thread.currentThread().isInterrupted()) {
                throw new TimeLimitExceededException(TIMEOUT_MESSAGE);
            }

            return inner.charAt(index);
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return inner.subSequence(start, end);
        }
    }
}
