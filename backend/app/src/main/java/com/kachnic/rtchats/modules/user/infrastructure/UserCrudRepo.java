package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface UserCrudRepo extends CrudRepository<UserJpa, UUID> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<UserJpa> findByEmailIgnoreCase(String email);
}
