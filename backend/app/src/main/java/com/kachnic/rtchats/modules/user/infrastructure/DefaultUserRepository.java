package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
class DefaultUserRepository implements UserRepository {

    private final UserCrudRepo userCrudRepo;
    private final UserMapper userMapper;

    public DefaultUserRepository(final UserCrudRepo userCrudRepo, final UserMapper userMapper) {
        this.userCrudRepo = userCrudRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserId nextId() {
        return UserId.of(UUID.randomUUID());
    }

    @Override
    public Optional<UserDto> findByEmail(final Email email) {
        return userCrudRepo.findByEmailIgnoreCase(email.value()).map(userMapper::toDto);
    }

    @Override
    public void save(final UserEntity entity) {
        final UserJpa userJpa = userMapper.toPersistence(entity);
        userCrudRepo.save(userJpa);
    }

    @Override
    public boolean existsByEmail(final Email email) {
        return userCrudRepo.existsByEmailIgnoreCase(email.value());
    }
}
