package com.hyf.cloud.controller;

import com.hyf.cloud.annotation.NotControllerResponseAdvice;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }

}
