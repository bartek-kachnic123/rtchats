package com.kachnic.rtchats.modules.user.web;

import com.kachnic.rtchats.libs.spring.MessageResolver;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import java.util.Locale;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
@AllArgsConstructor
class UserControllerAdvice {
    private final MessageResolver messageResolver;

    @ExceptionHandler(EmailAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* package */ Map<String, String> handleEmailValidation(final EmailAlreadyTakenException exc, final Locale locale) {
        final String message = messageResolver.resolveOrDefault(exc, locale);
        return Map.of(EmailAlreadyTakenException.getFieldName(), message);
    }
}
