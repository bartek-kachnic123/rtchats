package com.kachnic.rtchats.modules.user.infrastructure;

import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.domain.model.UserEntity;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserInfo;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;

interface UserJpaMapper {
    @Mapping(source = "entityId", target = "userId")
    @Mapping(source = "userInfo", target = ".")
    @Mapping(target = "normalizedEmail", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserJpa toPersistence(UserEntity entity);

    UserDto toDto(UserJpa userJpa);

    default UserEntity toEntity(final UserJpa userJpa) {
        final UserId userId = new UserId(userJpa.getUserId());
        final Email email = new Email(userJpa.getEmail());
        final Username username = new Username(userJpa.getUsername());
        final Password password = new Password(userJpa.getPassword());
        final UserInfo userInfo = new UserInfo(email, username, password);
        return new UserEntity(userId, userInfo);
    }
}
