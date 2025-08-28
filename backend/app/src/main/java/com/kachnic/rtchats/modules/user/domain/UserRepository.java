package com.kachnic.rtchats.modules.user.domain;

import java.util.Optional;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;

public interface UserRepository {
    UserId nextId();

    Optional<UserDto> findByEmail(Email email);

    void save(UserEntity entity);

    boolean existsByEmail(Email email);
}
