package com.kachnic.rtchats.modules.user.infrastructure.security;

import java.io.Serial;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import com.kachnic.rtchats.libs.spring.security.AppUserDetails;

import lombok.EqualsAndHashCode;

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

    AppUser(
            final UUID userId,
            final String email,
            final String password,
            final Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
