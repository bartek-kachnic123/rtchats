package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Locale;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "users",
        indexes = {@Index(name = "users_idx_normalized_email", columnList = "normalizedEmail")})
@Getter
@Setter
@NoArgsConstructor
class UserJpa {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String normalizedEmail;

    @Column(nullable = false, length = 32)
    private String username;

    @Column(nullable = false)
    private String password;

    @Version
    private long version;

    @PrePersist
    @PreUpdate
    /* package */ void normalizeEmail() {
        normalizedEmail = email.toLowerCase(Locale.ROOT);
    }
}
