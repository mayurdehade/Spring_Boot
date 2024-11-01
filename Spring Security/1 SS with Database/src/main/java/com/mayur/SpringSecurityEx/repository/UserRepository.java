package com.mayur.SpringSecurityEx.repository;

import com.mayur.SpringSecurityEx.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);
}
