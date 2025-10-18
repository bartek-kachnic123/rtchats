package com.kachnic.rtchats.modules.user.domain.service;

import com.kachnic.rtchats.libs.utils.Range;
import com.kachnic.rtchats.libs.utils.StringGuard;
import com.kachnic.rtchats.modules.user.domain.model.UserPatternRule;

public final class DefaultPasswordPolicy implements PasswordPolicy {

    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 255;

    public static final String MIN_ONE_UPPERCASE = "^(?=.*[A-Z]).*$";
    public static final String MIN_ONE_LOWERCASE = "^(?=.*[a-z]).*$";
    public static final String MIN_ONE_DIGIT = "^(?=.*[0-9]).*$";
    public static final String MIN_ONE_SPECIAL = "^(?=.*[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~]).*$";

    @Override
    public void validate(final String password) {
        final String context = DefaultPasswordPolicy.class.getSimpleName();
        StringGuard.assertNotBlank(password, context);
        StringGuard.assertLengthInRange(password, context, Range.ofInclusive(MIN_LENGTH, MAX_LENGTH));
        StringGuard.assertMatches(
                password,
                UserPatternRule.UPPERCASE,
                UserPatternRule.LOWERCASE,
                UserPatternRule.DIGIT,
                UserPatternRule.SPECIAL);
    }
}
