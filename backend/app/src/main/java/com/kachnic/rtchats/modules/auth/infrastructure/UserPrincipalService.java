package com.kachnic.rtchats.modules.auth.infrastructure;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class UserPrincipalService implements UserDetailsService {

    final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return users.findBy(new Email(username))
                .map(user -> new UserPrincipal(
                        user.getUserInfo().email().value(),
                        user.getUserInfo().password().value()))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
