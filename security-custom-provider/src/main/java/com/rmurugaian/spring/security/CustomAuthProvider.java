package com.rmurugaian.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rmurugaian 2019-05-13
 */
@Component
@Slf4j
public class CustomAuthProvider implements AuthenticationProvider {

    private static final Map<String, UserDetails> USERS = new ConcurrentHashMap<>();

    public CustomAuthProvider() {
        USERS.put("Ram", new CustomUserDetails("Ram", "Ramspassword", true, "USER"));
        USERS.put("Vijay", new CustomUserDetails("Vijay", "Vijayspassword", true, "USER", "ADMIN"));
        USERS.put("Ramesh", new CustomUserDetails("Ramesh", "Rameshspassword", false, "USER", "ADMIN"));
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {

        final String principal = (String) authentication.getPrincipal();
        final String credentials = (String) authentication.getCredentials();
        final UserDetails userDetails = USERS.get(principal);
        if (userDetails != null && userDetails.getPassword().equalsIgnoreCase(credentials)) {
            return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        }

        throw new BadCredentialsException(String.format("Couldn't authorize user %s", principal));
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -3440622656664401057L;

    private final String userName;
    private final String password;
    private final boolean active;
    private final Collection<GrantedAuthority> authorities;

    CustomUserDetails(final String userName, final String password, final boolean active, final String... authorities) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.authorities = Stream.of(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
