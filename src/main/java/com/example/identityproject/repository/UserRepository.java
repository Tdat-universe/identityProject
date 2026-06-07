package com.example.identityproject.repository;

import com.example.identityproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String name);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
