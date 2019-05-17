package com.rmurugaian.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author rmurugaian 2019-05-17
 */
@SpringBootApplication
public class WebMethodBasedApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WebMethodBasedApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {

        return jackson2ObjectMapperBuilder.modules(new Jdk8Module()).build();
    }
}
