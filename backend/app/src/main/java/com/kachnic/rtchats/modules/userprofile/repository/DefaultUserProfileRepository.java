package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
class DefaultUserProfileRepository implements UserProfileRepository {

    private final UserProfileJpaRepository profilesJpa;
    private final UserProfileMapper mapper;

    @Override
    public UserProfileId nextId() {
        return UserProfileId.of(UUID.randomUUID());
    }

    @Override
    public void save(final UserProfileEntity entity) {
        final UserProfileJpa profileJpa = mapper.toPersistence(entity);
        profilesJpa.save(profileJpa);
    }
}

interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UUID> {}
