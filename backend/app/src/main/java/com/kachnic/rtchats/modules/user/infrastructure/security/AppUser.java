package com.kachnic.rtchats.modules.user.infrastructure.security;

import java.io.Serial;
import java.util.Collection;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import com.kachnic.rtchats.libs.spring.security.AppUserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class AppUser implements AppUserDetails, CredentialsContainer {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String EMPTY_PASSWORD = "";

    @EqualsAndHashCode.Include
    private final UUID userId;

    private final String email;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    @JsonCreator
    AppUser(
            @JsonProperty("userId") final UUID userId,
            @JsonProperty("email") final String email,
            @JsonProperty("password") final String password,
            @JsonProperty("authorities") final Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public void eraseCredentials() {
        password = EMPTY_PASSWORD;
    }
}
