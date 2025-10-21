package com.kachnic.rtchats.modules.user.web;

import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import com.kachnic.rtchats.modules.user.domain.service.DefaultPasswordPolicy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@PasswordsMatch(message = "{user.passwords.match}")
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
