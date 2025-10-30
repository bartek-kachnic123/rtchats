package com.kachnic.rtchats.modules.user.application.exceptionhandlers;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kachnic.rtchats.libs.application.LogEventBus;
import com.kachnic.rtchats.libs.spring.MessageResolver;
import com.kachnic.rtchats.libs.spring.logging.DomainExceptionLogEvent;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
class UserExceptionHandler {

    private final MessageResolver messageResolver;
    private final LogEventBus logBus;

    @ExceptionHandler(EmailAlreadyTakenException.class)
    ProblemDetail handle(final EmailAlreadyTakenException exception, final Locale locale) {
        logBus.publish(new DomainExceptionLogEvent(exception));
        final String message = messageResolver.resolveOrDefault(exception, locale);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    }
}
