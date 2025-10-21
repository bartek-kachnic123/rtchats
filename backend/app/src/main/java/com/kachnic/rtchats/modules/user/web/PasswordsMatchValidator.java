package com.kachnic.rtchats.modules.user.web;

import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, CreateUserRequest> {

    @Override
    public boolean isValid(final CreateUserRequest request, final ConstraintValidatorContext context) {
        return Objects.equals(request.password(), request.confirmPassword());
    }
}
