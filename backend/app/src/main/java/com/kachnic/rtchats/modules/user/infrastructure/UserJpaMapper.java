package com.kachnic.rtchats.modules.user.infrastructure;

import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.domain.model.UserEntity;

interface UserJpaMapper {
    @Mapping(source = "entityId", target = "userId")
    @Mapping(source = "userInfo", target = ".")
    @Mapping(target = "normalizedEmail", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserJpa toPersistence(UserEntity entity);

    UserDto toDto(UserJpa userJpa);
}
