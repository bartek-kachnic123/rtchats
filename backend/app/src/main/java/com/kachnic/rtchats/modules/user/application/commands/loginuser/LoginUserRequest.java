package com.kachnic.rtchats.modules.user.application.commands.loginuser;

import com.kachnic.rtchats.modules.user.application.validation.EmailPattern;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record LoginUserRequest(
        @NotBlank @Size(max = LoginUserRequest.FIELD_MAX_SIZE) @EmailPattern String email,
        @NotBlank @Size(max = LoginUserRequest.FIELD_MAX_SIZE) String password) {
    private static final int FIELD_MAX_SIZE = 999;
}
