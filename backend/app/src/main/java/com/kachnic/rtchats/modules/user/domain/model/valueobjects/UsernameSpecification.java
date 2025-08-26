package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.specs.*;
import com.kachnic.rtchats.modules.user.domain.exceptions.UserErrorCode;
import java.util.regex.Pattern;

final class UsernameSpecification {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 32;

    private static final Pattern VALID_PATTERN = Pattern.compile(
            """
            (?x)              # Enable COMMENTS mode
            ^                 # Start of string
            [a-zA-Z]         # First character: letter
            [a-zA-Z0-9]*     # Following characters: letters and digits, zero or more
            $                 # End of string
            """);
    private static final long TIME_LIMIT_MS = 50L;

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(BetweenLengthSpecification.of(MIN_LENGTH, MAX_LENGTH))
            .and(TimeLimitSpecification.of(
                    MatchesFormatSpecification.of(VALID_PATTERN, UserErrorCode.INVALID_USERNAME), TIME_LIMIT_MS));

    private UsernameSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
