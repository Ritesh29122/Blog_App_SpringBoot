package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);  // Add method to find user by email
}
