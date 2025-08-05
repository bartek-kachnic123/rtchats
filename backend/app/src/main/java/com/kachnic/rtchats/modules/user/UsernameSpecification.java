package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.BetweenLengthSpecification;
import com.kachnic.rtchats.libs.ddd.specs.MatchesFormatSpecification;
import com.kachnic.rtchats.libs.ddd.specs.NotBlankSpecification;
import com.kachnic.rtchats.libs.ddd.specs.Specification;
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

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(BetweenLengthSpecification.of(MIN_LENGTH, MAX_LENGTH))
            .and(MatchesFormatSpecification.of(VALID_PATTERN));

    private UsernameSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
