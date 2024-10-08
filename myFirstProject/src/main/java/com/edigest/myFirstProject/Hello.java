package com.edigest.myFirstProject;

import org.springframework.stereotype.Component;

//transfer control to IOC
@Component
public class Hello {

    public String sayHello() {
        return "Hello";
    }
}
