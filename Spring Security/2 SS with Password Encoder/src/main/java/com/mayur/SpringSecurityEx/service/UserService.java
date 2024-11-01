package com.mayur.SpringSecurityEx.service;

import com.mayur.SpringSecurityEx.entity.Users;
import com.mayur.SpringSecurityEx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //creating object of BCryptPasswordEncoder (12 rounds)
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        //bcrypt library -> use for password encryption
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
