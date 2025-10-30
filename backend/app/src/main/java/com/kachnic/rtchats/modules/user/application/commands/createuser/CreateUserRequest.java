package com.kachnic.rtchats.modules.user.application.commands.createuser;

import com.kachnic.rtchats.modules.user.application.validation.EmailPattern;
import com.kachnic.rtchats.modules.user.application.validation.PasswordsMatch;
import com.kachnic.rtchats.modules.user.application.validation.StrongPassword;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;

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
                @Pattern(regexp = Username.ALLOWED_FORMAT, message = "{user.username.invalid}")
                String username,
        @StrongPassword String password,
        @NotBlank(message = "{user.confirm-password.not-blank}") String confirmPassword) {}
