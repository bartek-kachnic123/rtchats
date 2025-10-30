package com.kachnic.rtchats.modules.user.repository;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;
import com.kachnic.rtchats.modules.user.dtos.UserDto;

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(source = "entityId", target = "userId")
    @Mapping(target = "normalizedEmail", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserJpa toPersistence(UserEntity entity);

    UserDto toDto(UserJpa userJpa);

    default UserEntity toEntity(final UserJpa userJpa) {
        final UserId userId = new UserId(userJpa.getUserId());
        final Email email = new Email(userJpa.getEmail());
        final Username username = new Username(userJpa.getUsername());
        final Password password = new Password(userJpa.getPassword());
        return new UserEntity(userId, email, username, password);
    }

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
