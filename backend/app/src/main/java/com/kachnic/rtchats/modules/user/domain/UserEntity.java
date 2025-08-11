package com.kachnic.rtchats.modules.user.domain;

import com.kachnic.rtchats.libs.ddd.AggregateRoot;
import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.modules.user.domain.events.UserCreatedEvent;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserInfo;

public final class UserEntity extends AggregateRoot<UserId> {
    private final UserInfo userInfo;

    public static UserEntity create(final UserId userId, final UserInfo userInfo) {
        final UserEntity user = new UserEntity(userId, userInfo);
        user.addEvent(new UserCreatedEvent(user.getEntityId(), user.userInfo.email()));
        return user;
    }

    public UserEntity(final UserId userId, final UserInfo userInfo) {
        super(userId);
        this.userInfo = DomainValidate.requireNonNull(userInfo, UserInfo.class.getSimpleName());
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
