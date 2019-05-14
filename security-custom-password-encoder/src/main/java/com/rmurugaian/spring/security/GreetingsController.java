package com.rmurugaian.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author rmurugaian 2019-05-13
 */
@RestController
public class GreetingsController {

    @GetMapping("/greet")
    String greet(final Principal principal) {
        return String.format("Hi %s , You are logged in successfully!!", principal.getName());
    }
}
