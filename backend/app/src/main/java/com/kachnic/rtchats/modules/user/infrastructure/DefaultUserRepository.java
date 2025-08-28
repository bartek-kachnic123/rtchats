package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
class DefaultUserRepository implements UserRepository {

    private final UserCrudRepo userCrudRepo;
    private final UserJpaMapper mapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserId nextId() {
        return UserId.of(UUID.randomUUID());
    }

    @Override
    public Optional<UserDto> findByEmail(final Email email) {
        final String normalizedEmail = email.value().toLowerCase(Locale.ROOT);
        return userCrudRepo.findByNormalizedEmail(normalizedEmail).map(mapper::toDto);
    }

    @Override
    public void save(final UserEntity entity) {
        final UserJpa userJpa = mapper.toPersistence(entity);
        userCrudRepo.save(userJpa);
    }

    @Override
    public boolean existsByEmail(final Email email) {
        final String normalizedEmail = email.value().toLowerCase(Locale.ROOT);
        final Boolean result = jdbcTemplate.queryForObject(ExistsBy.NORMALIZED_EMAIL, Boolean.class, normalizedEmail);
        return Boolean.TRUE.equals(result);
    }

    private enum ExistsBy {
        ;

        public static final String NORMALIZED_EMAIL = "SELECT EXISTS (SELECT 1 FROM users WHERE normalized_email = ?)";
    }
}
