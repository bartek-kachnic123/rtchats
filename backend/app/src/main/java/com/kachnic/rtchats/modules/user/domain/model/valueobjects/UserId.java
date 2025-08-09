package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;
import java.util.UUID;

public record UserId(UUID value) {
    public UserId {
        DomainValidate.ifNull(value).thenThrow(() -> new MissingArgumentException(UserId.class.getSimpleName()));
    }

    public static UserId of(final UUID value) {
        return new UserId(value);
    }
}
