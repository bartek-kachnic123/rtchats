package com.kachnic.rtchats.modules.user.application;

import org.springframework.context.event.EventListener;

import com.kachnic.rtchats.libs.application.UseCaseExecutor;
import com.kachnic.rtchats.libs.spring.CommandHandler;
import com.kachnic.rtchats.modules.user.domain.model.UserEntity;

import lombok.AllArgsConstructor;

@CommandHandler
@AllArgsConstructor
class CreateUserCommandHandler {

    private final UseCaseExecutor<UserEntity, CreateUserCommand> useCase;
    private final UserEntityMapper mapper;

    @EventListener
    /* package */ void handle(final CreateUserCommand command) {
        final UserEntity user = useCase.execute(command);
        completeCommand(user, command);
    }

    private void completeCommand(final UserEntity user, final CreateUserCommand command) {
        final UserDto dto = mapper.toDto(user);
        command.complete(dto);
    }
}
