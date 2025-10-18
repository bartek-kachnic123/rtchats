package com.kachnic.rtchats.libs.spring.logging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class DomainExceptionListener {

    @EventListener
    public void handleDomain(final DomainLogEvent event) {
        log.atDebug().log(event::toString);
        log.atTrace().log(event::getStackTrace);
    }
}
