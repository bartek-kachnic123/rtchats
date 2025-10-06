package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.libs.application.AsyncCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CreateUserCommand extends AsyncCommand<UserDto> {

    private final String email;
    private final String username;
    private final String password;
}
