package com.kachnic.rtchats.modules.user.web.logging;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.kachnic.rtchats.libs.exceptions.DomainException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class ExceptionListener {

    @EventListener
    public void handleDomain(final ExceptionLogEvent<DomainException> event) {
        final Exception exception = event.exception();
        log(exception);
    }

    private void log(final Exception exception) {
        log.atDebug().log(exception::toString);
        log.trace("Stack trace: ", exception);
    }

    @EventListener
    public void handleMethodArgumentNotValidException(final ExceptionLogEvent<MethodArgumentNotValidException> event) {
        final MethodArgumentNotValidException exception = event.exception();
        log(exception);
        logErrors(exception);
    }

    private void logErrors(final MethodArgumentNotValidException exception) {
        exception.getFieldErrors().forEach(fieldError -> log.atWarn()
                .addArgument(fieldError::getField)
                .addArgument(fieldError::getCode)
                .log("Validation failed for field '{}': code={}"));
        exception.getGlobalErrors().forEach(globalError -> log.atWarn()
                .addArgument(globalError::getCode)
                .log("Global validation error: code={}"));
    }
}
