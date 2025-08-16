package com.kachnic.rtchats.modules.user.web;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
class UserControllerAdvice {
    private static final String MISSING_MSG = "Message is missing";
    private static final String MISSING_CODE = "Code is missing";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestBodyValidation(final MethodArgumentNotValidException exc) {
        return getGlobalErrors(exc);
    }

    private Map<String, String> getGlobalErrors(final MethodArgumentNotValidException exc) {
        return exc.getGlobalErrors().stream()
                .collect(Collectors.toUnmodifiableMap(
                        this::toCamelCaseCode,
                        err -> Optional.ofNullable(err.getDefaultMessage()).orElse(MISSING_MSG)));
    }

    private String toCamelCaseCode(final ObjectError err) {
        final String code = Optional.ofNullable(err.getCode()).orElse(MISSING_CODE);

        return code.length() > 1
                ? code.substring(0, 1).toLowerCase(Locale.ROOT) + code.substring(1)
                : code.toLowerCase(Locale.ROOT);
    }
}
