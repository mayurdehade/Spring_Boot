package com.jwt.SpringSecurityJWT.repository;

import com.jwt.SpringSecurityJWT.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {
}
