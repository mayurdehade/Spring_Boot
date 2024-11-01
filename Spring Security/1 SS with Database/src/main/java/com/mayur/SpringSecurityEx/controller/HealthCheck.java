package com.mayur.SpringSecurityEx.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping
    public String healthCheck(HttpServletRequest request) {
        //return session id
        return "Application is running... Session ID: " + request.getSession().getId();
    }
}
