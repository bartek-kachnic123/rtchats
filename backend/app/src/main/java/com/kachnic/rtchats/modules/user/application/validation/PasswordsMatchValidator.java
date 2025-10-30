package com.kachnic.rtchats.modules.user.application.validation;

import java.util.Objects;

import com.kachnic.rtchats.modules.user.application.commands.createuser.CreateUserRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, CreateUserRequest> {

    @Override
    public boolean isValid(final CreateUserRequest request, final ConstraintValidatorContext context) {
        return Objects.equals(request.password(), request.confirmPassword());
    }
}
