package com.kachnic.rtchats.libs.spring.security;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserDetails extends UserDetails {
    UUID getUserId();

    String getEmail();
}
