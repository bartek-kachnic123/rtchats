package com.kachnic.rtchats.modules.user.domain.events;

import java.util.UUID;

import com.kachnic.rtchats.libs.ddd.AbstractDomainEvent;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;

public final class UserCreatedEvent extends AbstractDomainEvent {

    private final UUID userId;
    private final String email;

    public UserCreatedEvent(final UserId userId, final Email email) {
        super();
        this.userId = userId.value();
        this.email = email.value();
    }

    public String getEmail() {
        return email;
    }

    public UUID getUserId() {
        return userId;
    }
}
