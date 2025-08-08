package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.modules.user.infrastructure.UserDto;
import java.util.Optional;

public interface UserRepository {
    UserId nextId();

    Optional<UserDto> findByEmail(Email email);

    void save(UserEntity entity);

    boolean existsByEmail(Email email);
}
