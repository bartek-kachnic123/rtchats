package com.kachnic.rtchats.modules.user.web;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kachnic.rtchats.libs.application.LogEventBus;
import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import com.kachnic.rtchats.libs.exceptions.InternalDomainException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.libs.spring.MessageResolver;
import com.kachnic.rtchats.modules.user.web.logging.ExceptionLogEvent;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ConcreteDomainExceptionHandler {

    private final MessageResolver messageResolver;
    private final LogEventBus logBus;

    @ExceptionHandler(InternalDomainException.class)
    ApiProblemDetail handleInternalServer(final InternalDomainException exception, final Locale locale) {
        logBus.publish(new ExceptionLogEvent<>(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        final ApiProblemDetail problemDetail = new ApiProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitleAndDetail(exception.getName(), localizedMessage);
        return problemDetail;
    }

    @ExceptionHandler(TimeLimitExceededException.class)
    ApiProblemDetail handleTimeLimitExceeded(final TimeLimitExceededException exception, final Locale locale) {
        logBus.publish(new ExceptionLogEvent<>(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        final ApiProblemDetail problemDetail = new ApiProblemDetail(HttpStatus.REQUEST_TIMEOUT);
        problemDetail.setTitleAndDetail(exception.getName(), localizedMessage);
        return problemDetail;
    }

    @ExceptionHandler(ArgumentInvalidFormatException.class)
    ApiProblemDetail handleArgumentInvalidFormat(final ArgumentInvalidFormatException exception, final Locale locale) {
        logBus.publish(new ExceptionLogEvent<>(exception));
        final String localizedMessage = messageResolver.resolveOrDefault(exception, locale);
        ApiProblemDetail problemDetail = new ApiProblemDetail(HttpStatus.BAD_REQUEST);
        problemDetail.setTitleAndDetail(exception.getName(), localizedMessage);
        return problemDetail;
    }
}
