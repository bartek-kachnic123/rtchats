package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

import java.util.UUID;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public record OwnerId(UUID value) {
    public OwnerId {
        ObjectGuard.assertNotNull(value, OwnerId.class.getSimpleName());
    }

    public static OwnerId of(final UUID value) {
        return new OwnerId(value);
    }
}
