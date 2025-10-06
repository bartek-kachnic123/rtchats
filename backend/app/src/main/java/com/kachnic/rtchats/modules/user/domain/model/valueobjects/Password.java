package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.utils.ObjectGuard;
import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;

public record Password(String value) {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 255;

    public Password {
        ObjectGuard.assertNotNull(value, Password.class.getSimpleName());
    }

    public static Password of(final String value) {
        return new Password(value);
    }

    public static void requireStrong(final String value) {
        StringGuard.assertNotBlank(value, Password.class.getSimpleName());
        StringGuard.assertLengthInRange(value, Password.class.getSimpleName(), Range.of(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertContains(
                value,
                UserPatternRule.UPPERCASE,
                UserPatternRule.LOWERCASE,
                UserPatternRule.DIGIT,
                UserPatternRule.SPECIAL);
    }
}
