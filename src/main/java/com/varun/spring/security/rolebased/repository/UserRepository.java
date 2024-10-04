package com.varun.spring.security.rolebased.repository;

import com.varun.spring.security.rolebased.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Make sure this returns Optional<User>
}
