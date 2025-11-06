package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.Avatar;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.OwnerId;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.ProfileLink;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

@Mapper(componentModel = "spring")
interface UserProfileMapper {
    @Mapping(source = "entityId", target = "userProfileId")
    @Mapping(target = "version", ignore = true)
    UserProfileJpa toPersistence(UserProfileEntity profile);

    @InheritInverseConfiguration
    @Mapping(target = "domainEvents", ignore = true)
    UserProfileEntity toDomain(UserProfileJpa profile);

    default UUID map(final UserProfileId profileId) {
        return profileId.value();
    }

    default UserProfileId mapUserProfileId(final UUID value) {
        return UserProfileId.of(value);
    }

    default UUID map(final OwnerId ownerId) {
        return ownerId.value();
    }

    default OwnerId mapOwnerId(final UUID value) {
        return OwnerId.of(value);
    }

    default String map(final Avatar avatar) {
        return avatar.value();
    }

    default Avatar mapAvatar(final String value) {
        return Avatar.of(value);
    }

    default String map(final ProfileLink link) {
        return link.value();
    }

    default ProfileLink mapProfileLink(final String value) {
        return ProfileLink.of(value);
    }
}
