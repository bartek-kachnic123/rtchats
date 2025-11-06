package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

import com.kachnic.rtchats.libs.utils.StringGuard;

public record ProfileLink(String value) {
    public ProfileLink {
        StringGuard.assertNotBlank(value, ProfileLink.class.getSimpleName());
    }

    public static ProfileLink of(final String value) {
        return new ProfileLink(value);
    }
}
