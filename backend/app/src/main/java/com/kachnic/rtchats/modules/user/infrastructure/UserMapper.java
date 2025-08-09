package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.modules.user.application.UserDto;
import com.kachnic.rtchats.modules.user.application.UserEntityMapper;
import com.kachnic.rtchats.modules.user.domain.UserEntity;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.UserId;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Username;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserMapper extends UserEntityMapper, UserJpaMapper {
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

interface UserJpaMapper {
    @Mapping(source = "entityId", target = "userId")
    @Mapping(source = "userInfo", target = ".")
    UserJpa toPersistence(UserEntity entity);

    UserDto toDto(UserJpa userJpa);
}
