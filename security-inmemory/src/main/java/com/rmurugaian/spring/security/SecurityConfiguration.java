package com.rmurugaian.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author rmurugaian 2019-05-09
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsManager userDetailsService() {
        final UserDetails userDetails =
            User.withDefaultPasswordEncoder().username("Ram").password("password").roles("USER").build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .formLogin()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated();
    }
}
