package com.rmurugaian.spring.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author rmurugaian 2019-05-13
 */
@RestController
public class PasswordRestController {

    private final Map<String, PasswordEncoder> passwordEncoders;

    public PasswordRestController(final Map<String, PasswordEncoder> passwordEncoders) {
        this.passwordEncoders = passwordEncoders;
    }

    @PostMapping("/encode")
    public String encode(@RequestBody final EncodeRequest encodeRequest) {

        return passwordEncoders.get(encodeRequest.getEncodeType()).encode(encodeRequest.getValue());
    }

}
