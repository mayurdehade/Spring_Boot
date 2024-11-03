package com.jwt.SpringSecurityJWT.service;

import com.jwt.SpringSecurityJWT.entity.Users;
import com.jwt.SpringSecurityJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //we require authentication manager for verify user details
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtservice;

    //encryption
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //verifying user login credentials
    public String verify(Users user) {
        //authentication manager take details and tell user is authenticated or not
        //create object of authentication
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //here pass username and password

        //if user is authenticated then it returns true
        if(authentication.isAuthenticated()) {
//            return "Success";
            //created a method for generating jwt token that required username
            return jwtservice.generateToken(user.getUsername());
        }
        return "Fail";
    }
}
