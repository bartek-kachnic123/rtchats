package com.kachnic.rtchats.modules.userprofile.application.eventhandlers;

import java.util.Locale;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.modules.user.domain.events.UserCreatedEvent;
import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.Avatar;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.DisplayName;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.OwnerId;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.ProfileLink;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;
import com.kachnic.rtchats.modules.userprofile.repository.UserProfileRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class CreateProfileHandler {

    private final UserProfileRepository profiles;

    @EventListener
    void createProfile(final UserCreatedEvent event) {
        final UserProfileId profileId = profiles.nextId();
        final OwnerId ownerId = OwnerId.of(event.getUserId());
        final String username = event.getUsername();
        final DisplayName displayName = DisplayName.of(username);
        final Avatar avatar = Avatar.empty();
        final String slug = username.toLowerCase(Locale.ROOT);
        final ProfileLink profileLink = ProfileLink.of(slug);
        final UserProfileEntity entity = UserProfileEntity.create(profileId, ownerId, displayName, avatar, profileLink);
        profiles.save(entity);
    }
}
