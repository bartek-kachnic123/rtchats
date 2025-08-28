package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import java.util.UUID;

import com.kachnic.rtchats.libs.ddd.DomainValidator;

public record UserId(UUID value) {
    public UserId {
        DomainValidator.assertNonNull(value, UserId.class.getSimpleName());
    }

    public static UserId of(final UUID value) {
        return new UserId(value);
    }
}
