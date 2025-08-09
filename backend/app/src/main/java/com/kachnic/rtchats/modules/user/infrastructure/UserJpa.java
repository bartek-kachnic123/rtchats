package com.kachnic.rtchats.modules.user.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserJpa {

    @Id
    private UUID userId;

    private String email;

    private String username;

    private String password;
}
