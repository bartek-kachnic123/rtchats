package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.AbstractDomainEvent;
import java.util.UUID;

final class UserCreatedEvent extends AbstractDomainEvent {
    private final UUID userId;
    private final String email;

    /* package */ UserCreatedEvent(final UserId userId, final Email email) {
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
