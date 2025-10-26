package com.kachnic.rtchats.modules.auth.web;

import com.kachnic.rtchats.modules.user.web.EmailPattern;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record LoginRequest(
        @NotBlank @Size(max = LoginRequest.FIELD_MAX_SIZE) @EmailPattern String email,
        @NotBlank @Size(max = LoginRequest.FIELD_MAX_SIZE) String password) {
    private static final int FIELD_MAX_SIZE = 999;
}
