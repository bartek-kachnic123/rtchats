package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;

public record Email(String value) {

    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 255;

    public Email {
        StringGuard.assertNotBlank(value, Email.class.getSimpleName());
        StringGuard.assertLengthInRange(value, Email.class.getSimpleName(), Range.of(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertMatches(value, UserPatternRule.EMAIL);
    }

    public static Email of(final String value) {
        return new Email(value);
    }

}
