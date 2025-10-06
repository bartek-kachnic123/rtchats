package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public record UserInfo(Email email, Username username, Password password) {
    public UserInfo {
        ObjectGuard.assertNotNull(email, Email.class.getSimpleName());
        ObjectGuard.assertNotNull(username, Username.class.getSimpleName());
        ObjectGuard.assertNotNull(password, Password.class.getSimpleName());
    }

    public static UserInfo of(final Email email, final Username username, final Password password) {
        return new UserInfo(email, username, password);
    }
}
