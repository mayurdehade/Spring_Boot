package com.jwt.SpringSecurityJWT.repository;

import com.jwt.SpringSecurityJWT.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users getByUsername(String username);
}
