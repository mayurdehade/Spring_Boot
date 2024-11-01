package com.mayur.SpringSecurityEx.service;

import com.mayur.SpringSecurityEx.entity.Users;
import com.mayur.SpringSecurityEx.model.UserPrinciples;
import com.mayur.SpringSecurityEx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//creating own class for implementing UserDetailsService for set our own user
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //we have to load user information
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //find user in database by userRepository and get it
        Users user = userRepository.findByUsername(username);

        //if not found throw exception
        if(user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciples(user);
    }
}
