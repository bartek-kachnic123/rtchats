package com.kachnic.rtchats.modules.user.application.commands.loginuser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kachnic.rtchats.libs.application.CommandBus;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("${api.paths.login}")
@AllArgsConstructor
class LoginUserController {

    private final CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void login(@Valid @RequestBody final LoginUserRequest request) {
        final LoginUserCommand command = new LoginUserCommand(request.email(), request.password());
        commandBus.execute(command);
    }
}
