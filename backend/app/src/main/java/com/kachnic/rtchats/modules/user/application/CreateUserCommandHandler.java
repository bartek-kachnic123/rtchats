package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.libs.ddd.UseCaseExecutor;
import com.kachnic.rtchats.libs.spring.CommandHandler;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

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
