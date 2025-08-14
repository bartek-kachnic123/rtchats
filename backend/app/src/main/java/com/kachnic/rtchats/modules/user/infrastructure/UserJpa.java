package com.kachnic.rtchats.modules.user.infrastructure;

import jakarta.persistence.*;
import java.util.Locale;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
class UserJpa {

    @Id
    private UUID userId;

    private String email;

    private String normalizedEmail;

    private String username;

    private String password;

    @PrePersist
    @PreUpdate
    /* package */ void normalizeEmail() {
        normalizedEmail = email.toLowerCase(Locale.ROOT);
    }
}
