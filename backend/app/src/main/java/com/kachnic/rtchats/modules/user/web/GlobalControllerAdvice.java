package com.kachnic.rtchats.modules.user.web;

import com.kachnic.rtchats.libs.spring.MessageResolver;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
class GlobalControllerAdvice {
    private static final String MISSING_CODE = "Code is missing";
    private final MessageResolver messageResolver;

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
        final String code =
                Optional.ofNullable(err.getCode()).orElseThrow(() -> new IllegalStateException(MISSING_CODE));

        return code.length() > 1
                ? code.substring(0, 1).toLowerCase(Locale.ROOT) + code.substring(1)
                : code.toLowerCase(Locale.ROOT);
    }
}
