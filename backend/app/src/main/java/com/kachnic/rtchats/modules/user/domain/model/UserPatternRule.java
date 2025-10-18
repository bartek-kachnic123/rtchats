package com.kachnic.rtchats.modules.user.domain.model;

import java.util.regex.Pattern;

import com.kachnic.rtchats.libs.utils.PatternRule;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import com.kachnic.rtchats.modules.user.domain.service.DefaultPasswordPolicy;

public enum UserPatternRule implements PatternRule {
    EMAIL(Email.ALLOWED_FORMAT, "Must be a valid email address"),
    USERNAME(Username.ALLOWED_FORMAT, "Can only contain letters and digits"),
    UPPERCASE(DefaultPasswordPolicy.MIN_ONE_UPPERCASE, "Must include at least one uppercase letter"),
    LOWERCASE(DefaultPasswordPolicy.MIN_ONE_LOWERCASE, "Must include at least one lowercase letter"),
    DIGIT(DefaultPasswordPolicy.MIN_ONE_DIGIT, "Must include at least one numeric digit"),
    SPECIAL(DefaultPasswordPolicy.MIN_ONE_SPECIAL, "Must include at least one special character"),
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
