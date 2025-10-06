package com.kachnic.rtchats.libs.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.libs.application.LogEventBus;

@Component
public class SpringLogEventBus implements LogEventBus {

    private final ApplicationEventPublisher publisher;

    public SpringLogEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public <T> void publish(T event) {
        publisher.publishEvent(event);
    }
}
