package com.kachnic.rtchats.modules.user.infrastructure.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.modules.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class AppUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        return users.findByEmail(email)
                .map(user -> new AppUser(
                        user.getEntityId().value(),
                        user.getEmail().value(),
                        user.getPassword().value(),
                        List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
