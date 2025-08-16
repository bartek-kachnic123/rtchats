package com.kachnic.rtchats.modules.user.web;

import com.kachnic.rtchats.libs.application.CommandBus;
import com.kachnic.rtchats.modules.user.application.CreateUserCommand;
import com.kachnic.rtchats.modules.user.application.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    private final CommandBus commandBus;

    public UserController(final CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    /* package */ ResponseEntity<UserDto> create(final @Valid @RequestBody CreateUserRequest request) {
        final CreateUserCommand command =
                new CreateUserCommand(request.email(), request.username(), request.password());
        final UserDto result = commandBus.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
