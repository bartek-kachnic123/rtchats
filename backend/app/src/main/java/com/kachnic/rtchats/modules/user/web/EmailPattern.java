package com.kachnic.rtchats.modules.user.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import static com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email.ALLOWED_FORMAT;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = {})
@Email
@Pattern(regexp = ALLOWED_FORMAT)
@ReportAsSingleViolation
public @interface EmailPattern {

    String message() default "Invalid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
