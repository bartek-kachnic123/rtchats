package com.kachnic.rtchats.modules.user.application.commands.createuser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.libs.application.CommandHandler;
import com.kachnic.rtchats.libs.application.time.TimeoutService;
import com.kachnic.rtchats.libs.application.time.UserTimeoutOperation;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;
import com.kachnic.rtchats.modules.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class CreateUserService implements CommandHandler<CreateUserCommand> {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final TimeoutService timeoutService;

    @Transactional
    @Override
    public void handle(final CreateUserCommand command) {
        final UserEntity user =
                timeoutService.executeWithTimeout(() -> createUser(command), UserTimeoutOperation.USER_CREATE);
        users.save(user);
    }

    private UserEntity createUser(final CreateUserCommand command) {
        final UserId userId = users.nextId();
        final Email email = Email.of(command.email());
        if (users.existsByEmail(email.value())) {
            throw new EmailAlreadyTakenException("Email is already taken!");
        }
        final Username username = Username.of(command.username());
        final String passwordHash = encoder.encode(command.password());
        final Password password = Password.of(passwordHash);
        return UserEntity.create(userId, email, username, password);
    }
}
