package com.kachnic.rtchats.modules.userprofile.domain;

import com.kachnic.rtchats.libs.ddd.AggregateRoot;
import com.kachnic.rtchats.libs.utils.ObjectGuard;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.Avatar;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.OwnerId;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.ProfileLink;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

public class UserProfileEntity extends AggregateRoot<UserProfileId> {

    private final OwnerId ownerId;
    private final Avatar avatar;
    private final ProfileLink profileLink;

    public UserProfileEntity(
            final UserProfileId userProfileId,
            final OwnerId ownerId,
            final Avatar avatar,
            final ProfileLink profileLink) {
        super(userProfileId);
        this.ownerId = ObjectGuard.requireNotNull(ownerId, OwnerId.class.getSimpleName());
        this.avatar = ObjectGuard.requireNotNull(avatar, Avatar.class.getSimpleName());
        this.profileLink = ObjectGuard.requireNotNull(profileLink, ProfileLink.class.getSimpleName());
    }

    public static UserProfileEntity create(
            final UserProfileId userProfileId,
            final OwnerId ownerId,
            final Avatar avatar,
            final ProfileLink profileLink) {
        return new UserProfileEntity(userProfileId, ownerId, avatar, profileLink);
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public ProfileLink getProfileLink() {
        return profileLink;
    }
}
