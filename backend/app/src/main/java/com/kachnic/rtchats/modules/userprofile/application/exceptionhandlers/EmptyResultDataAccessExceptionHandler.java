package com.kachnic.rtchats.modules.userprofile.application.exceptionhandlers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class EmptyResultDataAccessExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ProblemDetail handle(final EmptyResultDataAccessException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
