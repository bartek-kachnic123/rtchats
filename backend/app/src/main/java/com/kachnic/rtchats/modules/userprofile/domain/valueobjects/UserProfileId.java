package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

import java.util.UUID;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public record UserProfileId(UUID value) {
    public UserProfileId {
        ObjectGuard.assertNotNull(value, UserProfileId.class.getSimpleName());
    }

    public static UserProfileId of(final UUID value) {
        return new UserProfileId(value);
    }
}
