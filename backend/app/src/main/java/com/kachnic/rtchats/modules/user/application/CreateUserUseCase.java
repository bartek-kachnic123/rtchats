package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.libs.spring.UseCase;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.UserRepository;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.*;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
class CreateUserUseCase {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityMapper mapper;

    /* package */ CreateUserUseCase(
            final UserRepository users, final PasswordEncoder passwordEncoder, final UserEntityMapper mapper) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @EventListener
    /* package */ void handle(final CreateUserCommand command) {
        final Email email = Email.of(command.getEmail());
        if (users.existsByEmail(email)) {
            throw new EmailAlreadyTakenException(email.value());
        }

        final UserId userId = users.nextId();
        final Password password = Password.of(passwordEncoder.encode(command.getPassword()));
        final UserInfo userInfo = UserInfo.of(email, Username.of(command.getUsername()), password);

        final UserEntity user = UserEntity.create(userId, userInfo);
        users.save(user);

        final UserDto dto = mapper.toDto(user);
        command.complete(dto);
    }
}
