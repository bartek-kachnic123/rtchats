package com.kachnic.rtchats.modules.user.web;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kachnic.rtchats.libs.spring.MessageResolver;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class BeanValidationExceptionHandler {

    private static final String DEFAULT_KEY = "error";
    private final MessageResolver messageResolver;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemDetail handleRequestBodyValidation(final MethodArgumentNotValidException exception, final Locale locale) {
        ApiProblemDetail problemDetail = new ApiProblemDetail(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(exception.getObjectName());
        Map<String, String> errorMap = getMergedErrors(exception, locale);
        problemDetail.setErrors(errorMap);
        return problemDetail;
    }

    private Map<String, String> getMergedErrors(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new LinkedHashMap<>(getFieldErrors(ex, locale));
        errors.putAll(getGlobalErrors(ex, locale));
        return errors;
    }

    private Map<String, String> getFieldErrors(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError e : ex.getFieldErrors()) {
            fieldErrors.put(e.getField(), messageResolver.resolveOrDefault(e, locale));
        }
        return fieldErrors;
    }

    private Map<String, String> getGlobalErrors(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> globalErrors = new LinkedHashMap<>();
        for (ObjectError e : ex.getGlobalErrors()) {
            globalErrors.put(toCamelCaseKey(e.getCode()), messageResolver.resolveOrDefault(e, locale));
        }
        return globalErrors;
    }

    private String toCamelCaseKey(String key) {
        key = Objects.requireNonNullElse(key, DEFAULT_KEY);
        return key.length() > 1 ? Character.toLowerCase(key.charAt(0)) + key.substring(1) : key.toLowerCase();
    }
}
