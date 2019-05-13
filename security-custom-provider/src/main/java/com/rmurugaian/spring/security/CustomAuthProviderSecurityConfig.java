package com.rmurugaian.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author rmurugaian 2019-05-13
 */
@Configuration
public class CustomAuthProviderSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthProvider customAuthProvider;

    public CustomAuthProviderSecurityConfig(final CustomAuthProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthProvider);
    }

}
