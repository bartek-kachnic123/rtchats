package com.kachnic.rtchats.libs.spring;

import com.kachnic.rtchats.libs.application.CommandBus;
import com.kachnic.rtchats.libs.ddd.Command;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandBus implements CommandBus {

    private final ApplicationEventPublisher publisher;

    public SpringCommandBus(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public <R, C extends Command<R>> R execute(final C command) {
        publisher.publishEvent(command);
        return command.getResult();
    }
}
