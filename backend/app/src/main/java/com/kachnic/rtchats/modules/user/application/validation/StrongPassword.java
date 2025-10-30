package com.kachnic.rtchats.modules.user.application.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Documented
@NotBlank(message = "{user.password.not-blank}")
@Size(min = StrongPassword.MIN_LENGTH, max = StrongPassword.MAX_LENGTH, message = "{user.password.size}")
@Pattern.List({
    @Pattern(regexp = StrongPassword.MIN_ONE_UPPERCASE, message = "{user.password.missing-uppercase}"),
    @Pattern(regexp = StrongPassword.MIN_ONE_LOWERCASE, message = "{user.password.missing-lowercase}"),
    @Pattern(regexp = StrongPassword.MIN_ONE_DIGIT, message = "{user.password.missing-digit}"),
    @Pattern(regexp = StrongPassword.MIN_ONE_SPECIAL, message = "{user.password.missing-special}")
})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    int MIN_LENGTH = 8;
    int MAX_LENGTH = 255;

    String MIN_ONE_UPPERCASE = "^(?=.*[A-Z]).*$";
    String MIN_ONE_LOWERCASE = "^(?=.*[a-z]).*$";
    String MIN_ONE_DIGIT = "^(?=.*[0-9]).*$";
    String MIN_ONE_SPECIAL = "^(?=.*[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~]).*$";

    String message() default "Password does not meet strength requirements";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
