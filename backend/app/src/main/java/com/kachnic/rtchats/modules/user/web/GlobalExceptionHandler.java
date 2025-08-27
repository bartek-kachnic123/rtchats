package com.kachnic.rtchats.modules.user.web;

import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.InternalServerException;
import com.kachnic.rtchats.libs.exceptions.TimeLimitExceededException;
import com.kachnic.rtchats.libs.spring.MessageResolver;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
class GlobalExceptionHandler {

    private static final String DEFAULT_CODE = "default";
    private final MessageResolver messageResolver;

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String LOG_PREFIX = "Localized message: {}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* package */ Map<String, String> handleRequestBodyValidation(
            final MethodArgumentNotValidException exc, final Locale locale) {
        return getGlobalErrors(exc, locale);
    }

    private Map<String, String> getGlobalErrors(final MethodArgumentNotValidException exc, final Locale locale) {
        return exc.getGlobalErrors().stream()
                .collect(Collectors.toUnmodifiableMap(
                        this::toCamelCaseCode, err -> messageResolver.resolveOrDefault(err, locale)));
    }

    private String toCamelCaseCode(final ObjectError err) {
        final String code = Optional.ofNullable(err.getCode()).orElse(DEFAULT_CODE);

        return code.length() > 1
                ? code.substring(0, 1).toLowerCase(Locale.ROOT) + code.substring(1)
                : code.toLowerCase(Locale.ROOT);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    /* package */ ApiError handleInternalServerException(final InternalServerException exc, final Locale locale) {
        final String localizedMessage = messageResolver.resolveOrDefault(exc, locale);
        LOG.error(LOG_PREFIX, localizedMessage, exc);

        return ApiError.of(localizedMessage);
    }

    @ExceptionHandler(TimeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* package */ ApiError handleTimeLimitExceededException(final TimeLimitExceededException exc, final Locale locale) {
        final String localizedMessage = messageResolver.resolveOrDefault(exc, locale);

        LOG.atError()
                .addArgument(exc::getParamName)
                .addArgument(exc::getOperation)
                .addArgument(exc::getDurationMs)
                .log("TimeLimit exceeded: param={}, operation={}, durationMs={}");
        LOG.error(LOG_PREFIX, localizedMessage, exc);

        return ApiError.of(localizedMessage);
    }

    @ExceptionHandler(ArgumentInvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* package */ ApiError handleArgumentInvalidFormatException(
            final ArgumentInvalidFormatException exc, final Locale locale) {
        final String localizedMessage = messageResolver.resolveOrDefault(exc, locale);

        LOG.atDebug()
                .addArgument(exc::getParamName)
                .addArgument(exc::getInvalidValue)
                .log("Argument invalid format: param={}, invalidValue={}");
        LOG.atDebug().log(exc::toString);
        LOG.trace(LOG_PREFIX, localizedMessage, exc);

        return ApiError.of(localizedMessage);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* package */ ApiError handleDomainException(final DomainException exc, final Locale locale) {
        final String localizedMessage = messageResolver.resolveOrDefault(exc, locale);

        LOG.atDebug().log(exc::toString);
        LOG.trace(LOG_PREFIX, localizedMessage, exc);

        return ApiError.of(localizedMessage);
    }
}
