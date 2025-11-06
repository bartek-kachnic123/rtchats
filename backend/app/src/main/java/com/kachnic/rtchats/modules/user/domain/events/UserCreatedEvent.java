package com.kachnic.rtchats.modules.user.domain.events;

import java.util.UUID;

import com.kachnic.rtchats.libs.ddd.AbstractDomainEvent;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;

public final class UserCreatedEvent extends AbstractDomainEvent {

    private final UUID userId;
    private final String email;
    private final String username;

    public UserCreatedEvent(final UserId userId, final Email email, final Username username) {
        super();
        this.userId = userId.value();
        this.email = email.value();
        this.username = username.value();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
