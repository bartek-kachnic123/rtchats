package com.kachnic.rtchats.libs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import com.kachnic.rtchats.libs.exceptions.ArgumentOutOfRangeException;
import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;

public final class StringGuard {

    private StringGuard() {}

    public static void assertNotBlank(final String candidate, final String paramName) {
        ObjectGuard.assertNotNull(candidate, paramName);
        if (candidate.isBlank()) {
            throw new MissingArgumentException("Argument '" + paramName + "' is blank");
        }
    }

    public static void assertLengthInRange(final String candidate, final String paramName, final Range range) {
        final int actualLength = candidate.length();
        if (!range.contains(actualLength)) {
            final String message = String.format(
                    "Argument '%s' length is %d, but must be between %d and %d",
                    paramName, actualLength, range.min(), range.max());
            throw new ArgumentOutOfRangeException(message);
        }
    }

    public static void assertMatches(final String candidate, final PatternRule... rules) {
        for (final PatternRule rule : rules) {
            verifyRule(candidate, rule.getPattern(), rule.getPatternText());
        }
    }

    private static void verifyRule(final String candidate, final Pattern pattern, final String patternText) {
        final CharSequence sequence = new InterruptibleCharSequence(candidate);
        final Matcher matcher = pattern.matcher(sequence);
        if (!matcher.matches()) {
            throw new ArgumentInvalidFormatException(patternText);
        }
    }
}
