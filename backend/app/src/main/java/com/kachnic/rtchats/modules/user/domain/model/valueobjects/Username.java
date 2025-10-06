package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;

public record Username(String value) {

    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 32;

    public Username {
        StringGuard.assertNotBlank(value, Username.class.getSimpleName());
        StringGuard.assertLengthInRange(value, Username.class.getSimpleName(), Range.of(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertMatches(value, UserPatternRule.USERNAME);
    }

    public static Username of(final String value) {
        return new Username(value);
    }

}
