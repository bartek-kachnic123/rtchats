package com.kachnic.rtchats.modules.user.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;

import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;

@PasswordsMatch
public record CreateUserRequest(

        @NotBlank(message = "{user.email.notBlank}")
        @jakarta.validation.constraints.Email(message = "{user.email.invalid}")
        @Size(min = Email.MIN_LENGTH, max = Email.MAX_LENGTH, message = "{user.email.size}")
        String email,

        @NotBlank(message = "{user.username.notBlank}")
        @Size(min = Username.MIN_LENGTH, max = Username.MAX_LENGTH, message = "{user.username.size}")
        String username,

        @NotBlank(message = "{user.password.notBlank}")
        @Size(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH, message = "{user.password.size}")
        String password,

        @NotBlank(message = "{user.confirmPassword.notBlank}")
        String confirmPassword
) {}


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
