package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.specs.*;
import com.kachnic.rtchats.modules.user.domain.exceptions.UserErrorCode;

final class EmailSpecification {

    private static final int MAX_LENGTH = 255;
    private static final Pattern VALID_PATTERN = Pattern.compile(
            """
            (?x)            # Enable COMMENTS mode (ignore whitespace and allow comments)
            ^               # Start of string
            [^\\s@]+        # Local part: 1+ chars that are not whitespace or '@'
            @               # At symbol
            [^\\s@]+        # Domain: 1+ chars that are not whitespace or '@'
            \\.             # Literal dot
            [^\\s@]{2,}     # Top level domai: at least 2 chars, not whitespace or '@'
            $               # End of string
            """,
            Pattern.CASE_INSENSITIVE);
    private static final long TIME_LIMIT_MS = 50L;

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(MaxLengthSpecification.of(MAX_LENGTH))
            .and(TimeLimitSpecification.of(
                    MatchesFormatSpecification.of(VALID_PATTERN, UserErrorCode.INVALID_EMAIL), TIME_LIMIT_MS));

    private EmailSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
