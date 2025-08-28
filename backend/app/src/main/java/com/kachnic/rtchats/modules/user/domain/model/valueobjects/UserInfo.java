package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.ddd.DomainValidator;

public record UserInfo(Email email, Username username, Password password) {
    public UserInfo {
        DomainValidator.assertAllNotNull(
                email,
                Email.class.getSimpleName(),
                username,
                Username.class.getSimpleName(),
                password,
                Password.class.getSimpleName());
    }

    public static UserInfo of(final Email email, final Username username, final Password password) {
        return new UserInfo(email, username, password);
    }
}
