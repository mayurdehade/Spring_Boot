package com.edigest.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {

    //Dependency Injection - Field Injection
    @Autowired
    private Hello hello;

    @GetMapping("abc")
    public String sayHello() {
        return hello.sayHello();
    }
}
