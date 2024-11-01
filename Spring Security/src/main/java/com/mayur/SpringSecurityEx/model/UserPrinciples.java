package com.mayur.SpringSecurityEx.model;

import com.mayur.SpringSecurityEx.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//implementing UserDetails for set the own user details
public class UserPrinciples implements UserDetails {

    //created instance variable for user
    private Users user;

    //accept user from MyUserDetailsService
    public UserPrinciples(Users user) {
        this.user = user;
    }

    //roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return the user role
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //account is not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //account not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //credential not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //account is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
