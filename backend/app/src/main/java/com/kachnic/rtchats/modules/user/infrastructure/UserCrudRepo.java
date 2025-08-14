package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface UserCrudRepo extends CrudRepository<UserJpa, UUID> {
    boolean existsByNormalizedEmail(String normalizedEmail);

    Optional<UserJpa> findByNormalizedEmail(String normalizedEmail);
}
