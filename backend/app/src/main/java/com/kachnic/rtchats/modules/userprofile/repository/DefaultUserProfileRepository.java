package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
class DefaultUserProfileRepository implements UserProfileRepository {

    private final UserProfileJpaRepository profiles;
    private final UserProfileMapper mapper;
    private final ApplicationEventPublisher publisher;

    @Override
    public UserProfileId nextId() {
        return UserProfileId.of(UUID.randomUUID());
    }

    @Override
    public void save(final UserProfileEntity entity) {
        final UserProfileJpa profile = mapper.toPersistence(entity);
        profiles.save(profile);
        entity.getDomainEvents().forEach(publisher::publishEvent);
    }
}
