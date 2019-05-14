package com.rmurugaian.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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