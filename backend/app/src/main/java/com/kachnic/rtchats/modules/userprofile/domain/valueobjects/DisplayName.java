package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.StringGuard;

public record DisplayName(String value) {
    public DisplayName {
        StringGuard.assertNotBlank(value, DisplayName.class.getSimpleName());
    }

    public static DisplayName of(final String value) {
        return new DisplayName(value);
    }
}
