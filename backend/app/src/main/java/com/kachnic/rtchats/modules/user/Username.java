package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

record Username(String value) {
    Username {
        DomainValidate.ifBlank(value).thenThrow(() -> new MissingArgumentException(Username.class.getSimpleName()));
    }

    /* package */ static Username of(final String value) {
        return new Username(value);
    }
}
