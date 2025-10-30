package com.kachnic.rtchats.modules.user.domain.valueobjects;

import java.util.UUID;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public record UserId(UUID value) {
    public UserId {
        ObjectGuard.assertNotNull(value, UUID.class.getSimpleName());
    }

    public static UserId of(final UUID value) {
        return new UserId(value);
    }
}
