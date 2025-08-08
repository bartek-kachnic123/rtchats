package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.modules.user.Email;
import com.kachnic.rtchats.modules.user.UserEntity;
import com.kachnic.rtchats.modules.user.UserId;
import com.kachnic.rtchats.modules.user.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
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

    /* package */ interface UserCrudRepo extends CrudRepository<UserJpa, UUID> {
        boolean existsByEmailIgnoreCase(String email);

        Optional<UserJpa> findByEmailIgnoreCase(String email);
    }
}
