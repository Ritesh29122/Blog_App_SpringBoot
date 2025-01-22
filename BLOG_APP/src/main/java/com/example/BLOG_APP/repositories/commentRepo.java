package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepo extends JpaRepository<Comment,Integer> {
}
