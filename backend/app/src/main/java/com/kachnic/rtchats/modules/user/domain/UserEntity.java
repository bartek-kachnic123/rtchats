package com.kachnic.rtchats.modules.user.domain;

import com.kachnic.rtchats.libs.ddd.AggregateRoot;
import com.kachnic.rtchats.libs.utils.ObjectGuard;
import com.kachnic.rtchats.modules.user.domain.events.UserCreatedEvent;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;

public final class UserEntity extends AggregateRoot<UserId> {

    private final Email email;
    private final Username username;
    private final Password password;

    public static UserEntity create(
            final UserId userId, final Email email, final Username username, final Password password) {
        final UserEntity user = new UserEntity(userId, email, username, password);
        user.addEvent(new UserCreatedEvent(user.getEntityId(), user.getEmail()));
        return user;
    }

    public UserEntity(final UserId userId, final Email email, final Username username, final Password password) {
        super(userId);
        this.email = ObjectGuard.requireNotNull(email, Email.class.getSimpleName());
        this.username = ObjectGuard.requireNotNull(username, Username.class.getSimpleName());
        this.password = ObjectGuard.requireNotNull(password, Password.class.getSimpleName());
    }

    public Email getEmail() {
        return email;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
