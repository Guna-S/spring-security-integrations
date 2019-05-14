package com.rmurugaian.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rmurugaian 2019-05-13
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsPasswordService, UserDetailsService {

    private static final Map<String, UserDetails> USERS = new ConcurrentHashMap<>();

    public CustomUserDetailsService(final PasswordEncoder oldPasswordEncoder) {

        USERS.put(
            "Ram",
            new CustomUserDetails(
                "Ram",
                oldPasswordEncoder.encode("Ramspassword"),
                true,
                "USER"));
        USERS.put(
            "Vijay",
            new CustomUserDetails(
                "Vijay",
                oldPasswordEncoder.encode("Vijayspassword"),
                true,
                "USER", "ADMIN"));
        USERS.put(
            "Ramesh",
            new CustomUserDetails(
                "Ramesh",
                oldPasswordEncoder.encode("Rameshspassword"),
                false,
                "USER", "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {

        if (!USERS.containsKey(username)) {
            throw new UsernameNotFoundException(String.format("Couldn't find user %s", username));
        }

        return USERS.get(username);
    }

    @Override
    public UserDetails updatePassword(final UserDetails user, final String newPassword) {

        log.info("Update password prompted for user " + user.getUsername());

        final UserDetails userDetails =
            new CustomUserDetails(
                user.getUsername(),
                newPassword,
                user.isEnabled(),
                user.getAuthorities().stream().map(it -> ((GrantedAuthority) it).getAuthority()).toArray(String[]::new));

        USERS.put(user.getUsername(), userDetails);

        return loadUserByUsername(user.getUsername());
    }
}
