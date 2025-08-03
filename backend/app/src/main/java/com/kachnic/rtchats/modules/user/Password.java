package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

record Password(String value) {
    Password {
        DomainValidate.ifBlank(value).thenThrow(() -> new MissingArgumentException(Password.class.getSimpleName()));
    }

    /* package */ static Password of(final String value) {
        return new Password(value);
    }
}
