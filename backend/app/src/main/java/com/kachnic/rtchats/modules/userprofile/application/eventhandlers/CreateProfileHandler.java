package com.kachnic.rtchats.modules.userprofile.application.eventhandlers;

import java.util.Locale;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.modules.user.domain.events.UserCreatedEvent;
import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.Avatar;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.OwnerId;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.ProfileLink;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;
import com.kachnic.rtchats.modules.userprofile.repository.UserProfileRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class CreateProfileHandler {

    private final UserProfileRepository profiles;

    private static final String DEFAULT_AVATAR = "profile.png";

    @EventListener
    void createProfile(final UserCreatedEvent event) {
        final UserProfileId profileId = profiles.nextId();
        final OwnerId ownerId = new OwnerId(event.getUserId());
        final String slug = event.getUsername().toLowerCase(Locale.ROOT);
        final ProfileLink profileLink = new ProfileLink(slug);
        final Avatar avatar = new Avatar(DEFAULT_AVATAR);
        final UserProfileEntity entity = UserProfileEntity.create(profileId, ownerId, avatar, profileLink);
        profiles.save(entity);
    }
}
