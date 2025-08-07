package com.kachnic.rtchats.libs.ddd;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    Instant getOccurredAt();

    UUID getEventId();
}
