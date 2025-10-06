package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.utils.PatternRule;

enum UserPatternRule implements PatternRule {
    EMAIL(
            """
        (?ix)            # Enable CASE INSENSITIVE and COMMENTS mode (ignore whitespace and allow comments)
        ^               # Start of string
        [^\\s@]+        # Local part: 1+ chars that are not whitespace or '@'
        @               # At symbol
        [^\\s@]+        # Domain: 1+ chars that are not whitespace or '@'
        \\.             # Literal dot
        [^\\s@]{2,}     # Top level domain: at least 2 chars, not whitespace or '@'
        $               # End of string
        """,
            "Should have @"),

    USERNAME(
            """
        (?x)              # Enable COMMENTS mode
        ^                 # Start of string
        [a-zA-Z]          # First character: letter
        [a-zA-Z0-9]*      # Following characters: letters and digits, zero or more
        $                 # End of string
        """,
            "Should be one of the following characters: letters and digits"),

    UPPERCASE("[A-Z]", "big letters"),
    LOWERCASE("[a-z]", "lower letters"),
    DIGIT("[0-9]", "one digit"),
    SPECIAL("[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~]", "one special character"),
    ;

    private final Pattern pattern;
    private final String patternText;

    UserPatternRule(final String regex, final String patternText) {
        this.pattern = Pattern.compile(regex);
        this.patternText = patternText;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public String getPatternText() {
        return patternText;
    }
}
