package com.jwt.SpringSecurityJWT.controller;

import com.jwt.SpringSecurityJWT.entity.Users;
import com.jwt.SpringSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getStatus() {
        return "OK";
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        //validating username and password
        //generating jwt token
        return userService.verify(user);
    }
}
