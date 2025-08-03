package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.BaseEntity;
import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

final class UserEntity extends BaseEntity<UserId> {
    private final UserInfo userInfo;

    /* package */ static UserEntity create(final UserId userId, final UserInfo userInfo) {
        return new UserEntity(userId, userInfo);
    }

    private UserEntity(final UserId userId, final UserInfo userInfo) {
        super(userId);
        DomainValidate.ifNull(userInfo).thenThrow(() -> new MissingArgumentException(UserInfo.class.getSimpleName()));
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
