package com.kachnic.rtchats.modules.user.application;

import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.user.domain.UserEntity;

@FunctionalInterface
public interface UserEntityMapper {
    @Mapping(source = "userInfo", target = ".")
    UserDto toDto(UserEntity entity);
}
