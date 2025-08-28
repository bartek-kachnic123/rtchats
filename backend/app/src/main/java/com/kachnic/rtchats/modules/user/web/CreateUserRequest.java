package com.kachnic.rtchats.modules.user.web;

import java.lang.annotation.*;
import java.util.Objects;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@PasswordsMatch
public record CreateUserRequest(String email, String username, String password, String confirmPassword) {}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
@interface PasswordsMatch {
    String message() default "{user.passwords.match}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class PasswordMatchValidator implements ConstraintValidator<PasswordsMatch, CreateUserRequest> {

    @Override
    public boolean isValid(final CreateUserRequest request, final ConstraintValidatorContext context) {
        return Objects.equals(request.password(), request.confirmPassword());
    }
}
