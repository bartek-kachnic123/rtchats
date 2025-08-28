package com.kachnic.rtchats.modules.user.infrastructure;

import java.util.Locale;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

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
