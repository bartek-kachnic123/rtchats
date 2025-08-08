package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.modules.user.*;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "entityId", target = "userId")
    @Mapping(source = "userInfo", target = ".")
    UserJpa toPersistence(UserEntity userEntity);

    UserDto toDto(UserJpa userJpa);

    default UUID map(final UserId userId) {
        return userId.value();
    }

    default String map(final Email email) {
        return email.value();
    }

    default String map(final Username username) {
        return username.value();
    }

    default String map(final Password password) {
        return password.value();
    }
}
