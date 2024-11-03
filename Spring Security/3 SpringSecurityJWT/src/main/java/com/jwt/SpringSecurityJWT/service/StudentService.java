package com.jwt.SpringSecurityJWT.service;

import com.jwt.SpringSecurityJWT.entity.Students;
import com.jwt.SpringSecurityJWT.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Students> getStudents() {
        return studentRepository.findAll();
    }
}
