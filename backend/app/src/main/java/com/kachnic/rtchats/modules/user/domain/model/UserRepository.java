package com.kachnic.rtchats.modules.user.domain.model;

import java.util.Optional;

import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;

public interface UserRepository {
    UserId nextId();

    Optional<UserEntity> findBy(Email email);

    void save(UserEntity entity);

    boolean existsBy(Email email);
}
