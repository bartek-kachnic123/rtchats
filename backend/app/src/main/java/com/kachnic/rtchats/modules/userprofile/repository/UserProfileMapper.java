package com.kachnic.rtchats.modules.userprofile.repository;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kachnic.rtchats.modules.userprofile.domain.UserProfileEntity;

@Mapper(componentModel = "spring")
interface UserProfileMapper extends UserProfileFieldMapper {
    @Mapping(source = "entityId", target = "userProfileId")
    @Mapping(target = "version", ignore = true)
    UserProfileJpa toPersistence(UserProfileEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "domainEvents", ignore = true)
    UserProfileEntity toDomain(UserProfileJpa profile);
}
