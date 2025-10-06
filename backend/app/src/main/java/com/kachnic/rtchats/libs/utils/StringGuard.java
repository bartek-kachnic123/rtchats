package com.kachnic.rtchats.libs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import com.kachnic.rtchats.libs.exceptions.ArgumentOutOfRangeException;
import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;
import com.kachnic.rtchats.libs.exceptions.MissingCharacterException;

public final class StringGuard {

    private StringGuard() {}

    public static void assertNotBlank(final String candidate, final String paramName) {
        ObjectGuard.assertNotNull(candidate, paramName);
        if (candidate.isBlank()) {
            throw new MissingArgumentException("Argument '" + paramName + "' is blank");
        }
    }

    public static void assertLengthInRange(final String candidate, final String paramName, final Range range) {
        int actualLength = candidate.length();
        if (!range.containsInclusive(actualLength)) {
            final String message = String.format(
                    "Argument '%s' length is %d, but must be between %d and %d",
                    paramName, actualLength, range.min(), range.max());
            throw new ArgumentOutOfRangeException(message);
        }
    }

    public static void assertContains(final String candidate, final PatternRule... rules) {
        for (PatternRule rule : rules) {
            final Matcher matcher = getMatcher(candidate, rule);
            if (!matcher.find()) {
                throw new MissingCharacterException(rule.getPatternText());
            }
        }
    }

    public static void assertMatches(final String candidate, final PatternRule rule) {
        final Matcher matcher = getMatcher(candidate, rule);
        if (!matcher.matches()) {
            throw new ArgumentInvalidFormatException(rule.getPatternText());
        }
    }

    private static Matcher getMatcher(final String candidate, final PatternRule rule) {
        final CharSequence sequence = new InterruptibleCharSequence(candidate);
        final Pattern pattern = rule.getPattern();
        return pattern.matcher(sequence);
    }
}
