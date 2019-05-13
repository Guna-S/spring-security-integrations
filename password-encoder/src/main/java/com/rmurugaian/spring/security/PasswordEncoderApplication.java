package com.rmurugaian.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rmurugaian 2019-05-13
 */
@SpringBootApplication
public class PasswordEncoderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PasswordEncoderApplication.class, args);
    }

      @SuppressWarnings("deprecation")
    @Bean
    public Map<String, PasswordEncoder> passwordEncoders() {
        final String encodingId = "bcrypt";

        final Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put(null, NoOpPasswordEncoder.getInstance());
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new StandardPasswordEncoder());

        return encoders;
    }
}
