package com.kachnic.rtchats.modules.user.repository;

import java.util.Locale;
import java.util.UUID;

import com.kachnic.rtchats.modules.user.domain.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.valueobjects.Username;

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
        indexes = {@Index(name = "users_idx_normalized_email", columnList = "normalized_email")})
@Getter
@Setter
@NoArgsConstructor
class UserJpa {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID userId;

    @Column(nullable = false, unique = true, length = Email.MAX_LENGTH)
    private String email;

    @Column(nullable = false, unique = true, length = Email.MAX_LENGTH)
    private String normalizedEmail;

    @Column(nullable = false, length = Username.MAX_LENGTH)
    private String username;

    @Column(nullable = false)
    private String password;

    @Version
    private Long version;

    @PrePersist
    @PreUpdate
    void normalizeEmail() {
        normalizedEmail = email.toLowerCase(Locale.ROOT);
    }
}
