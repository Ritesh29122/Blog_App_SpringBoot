package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
