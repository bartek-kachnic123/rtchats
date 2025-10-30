package com.kachnic.rtchats.modules.user.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public record Password(String value) {

    public Password {
        ObjectGuard.assertNotNull(value, Password.class.getSimpleName());
    }

    public static Password of(final String value) {
        return new Password(value);
    }
}
