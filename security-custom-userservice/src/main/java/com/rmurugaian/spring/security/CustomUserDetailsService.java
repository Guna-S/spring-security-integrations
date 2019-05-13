package com.rmurugaian.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
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
public class CustomUserDetailsService implements UserDetailsService {

    private static final Map<String, UserDetails> USERS = new ConcurrentHashMap<>();

    public CustomUserDetailsService(final PasswordEncoder passwordEncoder) {

        USERS.put(
            "Ram",
            new CustomUserDetails(
                "Ram",
                passwordEncoder.encode("Ramspassword"),
                true,
                "USER"));
        USERS.put(
            "Vijay",
            new CustomUserDetails(
                "Vijay",
                passwordEncoder.encode("Vijayspassword"),
                true,
                "USER", "ADMIN"));
        USERS.put(
            "Ramesh",
            new CustomUserDetails(
                "Ramesh",
                passwordEncoder.encode("Rameshspassword"),
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
}
