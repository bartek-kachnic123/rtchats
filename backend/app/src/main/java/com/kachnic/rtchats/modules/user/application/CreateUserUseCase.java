package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.libs.application.UseCaseExecutor;
import com.kachnic.rtchats.libs.spring.UseCase;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.model.UserEntity;
import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserInfo;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import com.kachnic.rtchats.modules.user.domain.service.UserCredentialService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
class CreateUserUseCase implements UseCaseExecutor<UserEntity, CreateUserCommand> {

    private final UserRepository users;
    private final UserCredentialService userCredService;
    private final TimeoutService timeoutService;

    @RandomTimed(
            minMs = 125,
            maxMs = 150,
            delayOn = {EmailAlreadyTakenException.class})
    @Override
    public UserEntity execute(final CreateUserCommand command) {
        final UserId userId = users.nextId();
        final UserInfo userInfo =
                timeoutService.executeWithTimeout(() -> createUserInfo(command), UserTimeoutOperation.USER_INFO_CREATE);
        final UserEntity user = UserEntity.create(userId, userInfo);
        users.save(user);
        return user;
    }

    private UserInfo createUserInfo(final CreateUserCommand command) {
        final Email email = userCredService.createNewEmail(command.getEmail());
        final Password password = userCredService.createNewPassword(command.getPassword());
        return UserInfo.of(email, Username.of(command.getUsername()), password);
    }
}
