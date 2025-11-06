package com.kachnic.rtchats.modules.userprofile.repository;

import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;
import com.kachnic.rtchats.modules.userprofile.domain.valueobjects.UserProfileId;

public interface UserProfileRepository {
    UserProfileId nextId();

    void save(UserProfileEntity entity);
}
