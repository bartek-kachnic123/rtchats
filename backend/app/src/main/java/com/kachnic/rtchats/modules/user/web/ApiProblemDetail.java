package com.kachnic.rtchats.modules.user.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ApiProblemDetail extends ProblemDetail {

    private static final String ERRORS_KEY = "errors";

    public ApiProblemDetail(HttpStatus status) {
        super(status.value());
    }

    public void setTitleAndDetail(final String title, final String detail) {
        setTitle(title);
        setDetail(detail);
    }

    public void setErrors(final Map<String, String> errorMap) {
        setProperty(ERRORS_KEY, errorMap);
    }

    public void setErrors(final List<Map<String, String>> errorMapList) {
        setProperty(ERRORS_KEY, errorMapList);
    }
}
