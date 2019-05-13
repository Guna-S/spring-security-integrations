package com.rmurugaian.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author rmurugaian 2019-05-13
 */
@RestController
public class GreetingsController {

    @GetMapping("/")
    String greet(final Principal principal) {
        return "Hi ".concat(principal.getName()).concat(", You are logged in successfully !!");
    }
}
