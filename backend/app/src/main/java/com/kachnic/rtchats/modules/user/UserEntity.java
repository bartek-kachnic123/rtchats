package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.AggregateRoot;
import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

public final class UserEntity extends AggregateRoot<UserId> {
    private final UserInfo userInfo;

    /* package */ static UserEntity create(final UserId userId, final UserInfo userInfo) {
        final UserEntity user = new UserEntity(userId, userInfo);
        user.addEvent(new UserCreatedEvent(user.getEntityId(), user.userInfo.email()));
        return user;
    }

    public UserEntity(final UserId userId, final UserInfo userInfo) {
        super(userId);
        DomainValidate.ifNull(userInfo).thenThrow(() -> new MissingArgumentException(UserInfo.class.getSimpleName()));
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
