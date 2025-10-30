package com.kachnic.rtchats.modules.user.repository;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
class DefaultUserRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserId nextId() {
        return UserId.of(UUID.randomUUID());
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final String normalizedEmail = email.toLowerCase(Locale.ROOT);
        return userJpaRepository.findByNormalizedEmail(normalizedEmail).map(mapper::toEntity);
    }

    @Override
    public void save(final UserEntity entity) {
        final UserJpa userJpa = mapper.toPersistence(entity);
        userJpaRepository.save(userJpa);
    }

    @Override
    public boolean existsByEmail(final String email) {
        final String normalizedEmail = email.toLowerCase(Locale.ROOT);
        final Boolean result = jdbcTemplate.queryForObject(ExistsBy.NORMALIZED_EMAIL, Boolean.class, normalizedEmail);
        return Boolean.TRUE.equals(result);
    }

    private enum ExistsBy {
        ;

        public static final String NORMALIZED_EMAIL = "SELECT EXISTS (SELECT 1 FROM users WHERE normalized_email = ?)";
    }
}
