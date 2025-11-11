package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.Avatar;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.DisplayName;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.OwnerId;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.ProfileLink;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

interface UserProfileFieldMapper {

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

    default String map(final DisplayName displayName) {
        return displayName.value();
    }

    default DisplayName mapDisplayName(final String value) {
        return DisplayName.of(value);
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
