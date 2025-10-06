package com.kachnic.rtchats.modules.user.web;

import java.util.Locale;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kachnic.rtchats.libs.application.LogEventBus;
import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.spring.MessageResolver;
import com.kachnic.rtchats.modules.user.web.logging.ExceptionLogEvent;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
class DomainExceptionHandler {

    private final MessageResolver messageResolver;
    private final LogEventBus logBus;

    @ExceptionHandler(DomainException.class)
    ApiProblemDetail handleDomain(final DomainException exception, final Locale locale) {
        logBus.publish(new ExceptionLogEvent<>(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        ApiProblemDetail problemDetail = new ApiProblemDetail(HttpStatus.BAD_REQUEST);
        problemDetail.setTitleAndDetail(exception.getName(), localizedMessage);
        return problemDetail;
    }
}
