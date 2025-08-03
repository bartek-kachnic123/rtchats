package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;
import java.util.UUID;

record UserId(UUID value) {
    UserId {
        DomainValidate.ifNull(value).thenThrow(() -> new MissingArgumentException(UUID.class.getSimpleName()));
    }
}
