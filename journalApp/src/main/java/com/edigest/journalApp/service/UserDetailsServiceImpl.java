package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //find user by username
        User user = userRepository.findByUsername(username);

        //check is user details is found with username or not
        if(user != null) {
            //create userDetails object for return
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    //build object and set username, password and role
                    .username(user.getUsername())
                    .password(user.getPassword())
                    //this required roles in string with comma separated values
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        //if user not found then throw error
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
