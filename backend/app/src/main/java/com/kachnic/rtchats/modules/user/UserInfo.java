package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

record UserInfo(Email email, Username username, Password password) {
    UserInfo {
        DomainValidate.ifNull(email).thenThrow(() -> new MissingArgumentException(Email.class.getSimpleName()));
        DomainValidate.ifNull(username).thenThrow(() -> new MissingArgumentException(Username.class.getSimpleName()));
        DomainValidate.ifNull(password).thenThrow(() -> new MissingArgumentException(Password.class.getSimpleName()));
    }

    /* package */ static UserInfo of(final Email email, final Username username, final Password password) {
        return new UserInfo(email, username, password);
    }
}
