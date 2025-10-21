package com.kachnic.rtchats.modules.user.web;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kachnic.rtchats.libs.application.LogEventBus;
import com.kachnic.rtchats.libs.exceptions.InternalException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.libs.spring.MessageResolver;
import com.kachnic.rtchats.libs.spring.logging.InternalExceptionLogEvent;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
class InternalExceptionHandler {

    private final LogEventBus logBus;
    private final MessageResolver messageResolver;

    @ExceptionHandler(InternalException.class)
    ProblemDetail handle(final InternalException exception, final Locale locale) {
        logBus.publish(new InternalExceptionLogEvent(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, localizedMessage);
    }

    @ExceptionHandler(TimeLimitExceededException.class)
    ProblemDetail handle(final TimeLimitExceededException exception, final Locale locale) {
        logBus.publish(new InternalExceptionLogEvent(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        return ProblemDetail.forStatusAndDetail(HttpStatus.REQUEST_TIMEOUT, localizedMessage);
    }
}
