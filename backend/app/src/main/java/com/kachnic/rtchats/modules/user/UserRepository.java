package com.kachnic.rtchats.modules.user;

import java.util.Optional;
import java.util.UUID;

interface UserRepository {
    UUID nextId();

    Optional<UserEntity> getByEmail(String email);

    boolean existsByEmail(String email);
}
