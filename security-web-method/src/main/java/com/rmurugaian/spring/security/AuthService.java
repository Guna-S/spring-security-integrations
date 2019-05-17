package com.rmurugaian.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("authz")
@Slf4j
class AuthService {

    public boolean check(final CustomUserDetails customUserDetails) {
        log.info("validate the user " + customUserDetails.getUsername());
        return true;
    }
}