package com.kachnic.rtchats.modules.user.application;

import com.kachnic.rtchats.modules.user.domain.UserEntity;
import org.mapstruct.Mapping;

@FunctionalInterface
public interface UserEntityMapper {
    @Mapping(source = "userInfo", target = ".")
    UserDto toDto(UserEntity entity);
}
