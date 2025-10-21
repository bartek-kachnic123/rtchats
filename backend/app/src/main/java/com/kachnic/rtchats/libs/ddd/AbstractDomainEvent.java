package com.kachnic.rtchats.libs.ddd;

import java.time.Instant;
import java.util.UUID;

public class AbstractDomainEvent implements DomainEvent {

    private final Instant occurredAt = Instant.now();
    private final UUID eventId = UUID.randomUUID();

    @Override
    public Instant getOccurredAt() {
        return occurredAt;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }
}
