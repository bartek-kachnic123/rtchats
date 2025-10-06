package com.kachnic.rtchats.modules.user.application;

import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.user.domain.model.UserEntity;

@FunctionalInterface
public interface UserEntityMapper {
    @Mapping(source = "userInfo", target = ".")
    UserDto toDto(UserEntity entity);
}
