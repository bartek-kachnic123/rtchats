package com.kachnic.rtchats.modules.user.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;
import com.kachnic.rtchats.modules.user.domain.UserPatternRule;

public record Username(String value) {

    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 32;

    public static final String ALLOWED_FORMAT =
            """
        (?x)              # Enable COMMENTS mode
        ^                 # Start of string
        [a-zA-Z]          # First character: letter
        [a-zA-Z0-9]*      # Following characters: letters and digits, zero or more
        $                 # End of string
        """;

    public Username {
        StringGuard.assertNotBlank(value, Username.class.getSimpleName());
        StringGuard.assertLengthInRange(
                value, Username.class.getSimpleName(), Range.ofInclusive(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertMatches(value, UserPatternRule.USERNAME);
    }

    public static Username of(final String value) {
        return new Username(value);
    }
}
