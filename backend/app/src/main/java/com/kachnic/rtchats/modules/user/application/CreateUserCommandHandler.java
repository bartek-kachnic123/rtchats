package com.kachnic.rtchats.modules.user.application;

import org.springframework.stereotype.Service;

import com.kachnic.rtchats.libs.application.CommandHandler;
import com.kachnic.rtchats.libs.application.UseCaseExecutor;
import com.kachnic.rtchats.modules.user.domain.model.UserEntity;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UseCaseExecutor<UserEntity, CreateUserCommand> useCase;
    private final TimeoutService timeoutService;

    @Transactional
    @Override
    public void handle(final CreateUserCommand command) {
        timeoutService.executeWithTimeout(() -> useCase.execute(command), UserTimeoutOperation.USER_CREATE);
    }
}
