package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.StringGuard;

public record Avatar(String value) {
    public Avatar {
        StringGuard.assertNotBlank(value, Avatar.class.getSimpleName());
    }

    public static Avatar of(final String value) {
        return new Avatar(value);
    }
}
