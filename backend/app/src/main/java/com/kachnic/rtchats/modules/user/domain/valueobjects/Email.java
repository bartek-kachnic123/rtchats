package com.kachnic.rtchats.modules.user.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;
import com.kachnic.rtchats.modules.user.domain.UserPatternRule;

public record Email(String value) {

    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 320;

    public static final String ALLOWED_FORMAT =
            """
        (?ix)           # Enable CASE INSENSITIVE and COMMENTS mode (ignore whitespace and allow comments)
        ^               # Start of string
        [^\\s@]+        # Local part: 1+ chars that are not whitespace or '@'
        @               # At symbol
        [^\\s@]+        # Domain: 1+ chars that are not whitespace or '@'
        \\.             # Literal dot
        [^\\s@]{2,}     # Top level domain: at least 2 chars, not whitespace or '@'
        $               # End of string
        """;

    public Email {
        StringGuard.assertNotBlank(value, Email.class.getSimpleName());
        StringGuard.assertLengthInRange(value, Email.class.getSimpleName(), Range.ofInclusive(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertMatches(value, UserPatternRule.EMAIL);
    }

    public static Email of(final String value) {
        return new Email(value);
    }
}
