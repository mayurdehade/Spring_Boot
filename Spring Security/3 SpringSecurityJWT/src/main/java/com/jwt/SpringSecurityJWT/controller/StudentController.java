package com.jwt.SpringSecurityJWT.controller;

import com.jwt.SpringSecurityJWT.entity.Students;
import com.jwt.SpringSecurityJWT.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Students> getStudents() {
        return studentService.getStudents();
    }
}
