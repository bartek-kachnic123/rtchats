package com.kachnic.rtchats.modules.user;

import java.util.Optional;

interface UserRepository {
    UserId nextId();

    Optional<UserEntity> getByEmail(Email email);

    boolean existsByEmail(Email email);
}
