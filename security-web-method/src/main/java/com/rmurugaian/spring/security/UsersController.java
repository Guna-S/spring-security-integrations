package com.rmurugaian.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmurugaian 2019-05-17
 */
@RestController
public class UsersController {

    @GetMapping("/users/{name}")
    public String name(@PathVariable final String name) {

        return "User Name :".concat(name);
    }
}
