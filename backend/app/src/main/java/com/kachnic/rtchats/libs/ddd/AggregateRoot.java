package com.kachnic.rtchats.libs.ddd;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot<T> extends EntityBase<T> {

    private final List<DomainEvent> domainEvents;

    protected AggregateRoot(final T entityId) {
        super(entityId);
        this.domainEvents = new ArrayList<>();
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    protected void addEvent(final DomainEvent event) {
        domainEvents.add(event);
    }

    protected void clearEvents() {
        domainEvents.clear();
    }
}
