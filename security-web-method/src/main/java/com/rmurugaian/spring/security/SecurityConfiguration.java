package com.rmurugaian.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author rmurugaian 2019-05-17
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/employees").access("@authz.check(principal)")
                .mvcMatchers("/users/{name}").access("#name == principal?.username")
            .anyRequest().permitAll();

    }
    // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}


