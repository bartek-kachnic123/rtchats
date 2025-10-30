package com.kachnic.rtchats.modules.user.repository;

import java.util.Optional;

import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;

public interface UserRepository {
    UserId nextId();

    Optional<UserEntity> findByEmail(String email);

    void save(UserEntity entity);

    boolean existsByEmail(String email);
}
