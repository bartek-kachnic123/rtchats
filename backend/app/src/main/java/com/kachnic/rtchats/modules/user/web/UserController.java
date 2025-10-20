package com.kachnic.rtchats.modules.user.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kachnic.rtchats.libs.application.CommandBus;
import com.kachnic.rtchats.modules.user.application.CreateUserCommand;
import com.kachnic.rtchats.modules.user.application.UserDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("${api.paths.users}")
@AllArgsConstructor
class UserController {

    private final CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto create(final @Valid @RequestBody CreateUserRequest request) {
        final CreateUserCommand command =
                new CreateUserCommand(request.email(), request.username(), request.password());
        return commandBus.execute(command);
    }
}
