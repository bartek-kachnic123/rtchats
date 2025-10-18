package com.kachnic.rtchats.modules.user.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import com.kachnic.rtchats.modules.user.domain.service.DefaultPasswordPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@PasswordsMatch
public record CreateUserRequest(
        @NotBlank(message = "{user.email.not-blank}")
                @Size(min = Email.MIN_LENGTH, max = Email.MAX_LENGTH, message = "{user.email.size}")
                @EmailPattern(message = "{user.email.invalid}")
                String email,
        @NotBlank(message = "{user.username.not-blank}")
                @Size(min = Username.MIN_LENGTH, max = Username.MAX_LENGTH, message = "{user.username.size}")
                @Pattern(regexp = Username.ALLOWED_FORMAT, message = "user.username.format")
                String username,
        @NotBlank(message = "{user.password.not-blank}")
                @Size(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH, message = "{user.password.size}")
                @Pattern.List({
                    @Pattern(
                            regexp = DefaultPasswordPolicy.MIN_ONE_UPPERCASE,
                            message = "{user.password.missing-uppercase}"),
                    @Pattern(
                            regexp = DefaultPasswordPolicy.MIN_ONE_LOWERCASE,
                            message = "{user.password.missing-lowercase}"),
                    @Pattern(regexp = DefaultPasswordPolicy.MIN_ONE_DIGIT, message = "{user.password.missing-digit}"),
                    @Pattern(
                            regexp = DefaultPasswordPolicy.MIN_ONE_SPECIAL,
                            message = "{user.password.missing-special}")
                })
                String password,
        @NotBlank(message = "{user.confirm-password.not-blank}") String confirmPassword) {}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PasswordMatchValidator.class)
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
