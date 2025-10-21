package com.kachnic.rtchats.libs.spring.logging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class DomainExceptionLogListener {

    @EventListener
    public void handleDomain(final DomainExceptionLogEvent event) {
        log.atDebug().log(event::toString);
        log.atTrace().log(event::getStackTrace);
    }
}
