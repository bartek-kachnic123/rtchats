package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
class UserProfileJpa {

    @Id
    private UUID userProfileId;

    @Column(nullable = false, unique = true)
    private UUID ownerId;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false, unique = true)
    private String profileLink;

    @Version
    private Long version;
}
