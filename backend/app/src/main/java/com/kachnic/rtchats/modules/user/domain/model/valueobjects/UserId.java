package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import java.util.UUID;

public record UserId(UUID value) {
    public UserId {
        DomainValidate.assertNonNull(value, UserId.class.getSimpleName());
    }

    public static UserId of(final UUID value) {
        return new UserId(value);
    }
}
