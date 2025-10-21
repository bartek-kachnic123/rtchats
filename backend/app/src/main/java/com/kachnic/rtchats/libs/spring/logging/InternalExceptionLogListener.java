package com.kachnic.rtchats.libs.spring.logging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class InternalExceptionLogListener {

    @EventListener
    public void handleInternal(final InternalExceptionLogEvent event) {
        log.atError().log(event::toString);
        log.atError().log(event::getStackTrace);
    }
}
