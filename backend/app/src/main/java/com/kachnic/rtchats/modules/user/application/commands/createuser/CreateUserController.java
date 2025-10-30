package com.kachnic.rtchats.modules.user.application.commands.createuser;

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
@RequestMapping("${api.paths.users}")
@AllArgsConstructor
class CreateUserController {

    private final CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(final @Valid @RequestBody CreateUserRequest request) {
        final CreateUserCommand command =
                new CreateUserCommand(request.email(), request.username(), request.password());
        commandBus.execute(command);
    }
}
