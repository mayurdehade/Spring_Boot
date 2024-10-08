package com.edigest.myFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//this annotation is combination of 3 annotation
//@Configuration -> configure our project
//@EnableAutoConfiguration -> automatically configure dependencies and other things
//@ComponentScan -> scan the component and add into IOC container
@SpringBootApplication
public class MyFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

}
