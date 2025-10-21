package com.kachnic.rtchats.modules.user.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PasswordsMatchValidator.class)
@interface PasswordsMatch {
    String message() default "Password does not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
