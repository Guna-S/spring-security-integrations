package com.rmurugaian.spring.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

/**
 * @author rmurugaian 2019-05-10
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(final DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public InitializingBean initializingBean(
        final UserDetailsManager userDetailsManager,
        final PasswordEncoder passwordEncoder) {

        return () -> {
            final UserDetails userDetails =
                User.builder()
                    .passwordEncoder(passwordEncoder::encode)
                    .username("Ram")
                    .password("password")
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(userDetails);
        };
    }
}
