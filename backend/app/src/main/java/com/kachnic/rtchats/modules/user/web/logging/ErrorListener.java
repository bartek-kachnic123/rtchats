package com.kachnic.rtchats.modules.user.web.logging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.InternalDomainException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class ErrorListener {

    @EventListener
    public void handleTimeLimitExceeded(final ExceptionLogEvent<TimeLimitExceededException> event) {
        final TimeLimitExceededException exception = event.exception();
        log.atError()
                .addArgument(exception::getOperation)
                .addArgument(exception::getDurationMs)
                .log("TimeLimit exceeded: operation={}, durationMs={}");
        log(exception);
    }

    private void log(final DomainException exception) {
        log.atError().log(exception::toString);
        log.error("Stack trace: ", exception);
    }

    @EventListener
    public void handleInternalServer(final ExceptionLogEvent<InternalDomainException> event) {
        log(event.exception());
    }
}
