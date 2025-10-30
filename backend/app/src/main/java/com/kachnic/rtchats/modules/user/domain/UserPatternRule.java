package com.kachnic.rtchats.modules.user.domain;

import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.utils.PatternRule;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;

public enum UserPatternRule implements PatternRule {
    EMAIL(Email.ALLOWED_FORMAT, "Must be a valid email address"),
    USERNAME(Username.ALLOWED_FORMAT, "Can only contain letters and digits"),
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
