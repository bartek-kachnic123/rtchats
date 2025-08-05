package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.MatchesFormatSpecification;
import com.kachnic.rtchats.libs.ddd.specs.MaxLengthSpecification;
import com.kachnic.rtchats.libs.ddd.specs.NotBlankSpecification;
import com.kachnic.rtchats.libs.ddd.specs.Specification;
import java.util.regex.Pattern;

final class EmailSpecification {
    private static final int MAX_LENGTH = 128;
    private static final Pattern VALID_PATTERN = Pattern.compile(
            """
            (?x)         # Enable COMMENTS mode (ignore whitespace and allow comments)
            ^            # Start of string
            .+           # Local part
            @            # At symbol
            .+           # Domain
            \\.          # Dot
            .+           # Top-Level Domain e.g. com, org, net
            $            # End of string
            """,
            Pattern.CASE_INSENSITIVE);

    private static final Specification<String> DEFAULT = NotBlankSpecification.of()
            .and(MaxLengthSpecification.of(MAX_LENGTH))
            .and(MatchesFormatSpecification.of(VALID_PATTERN));

    private EmailSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
