package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

interface UserJpaRepository extends CrudRepository<UserJpa, UUID> {
    Optional<UserJpa> findByNormalizedEmail(String normalizedEmail);
}
