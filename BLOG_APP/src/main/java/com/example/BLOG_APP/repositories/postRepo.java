package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.Category;
import com.example.BLOG_APP.models.User;
import com.example.BLOG_APP.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRepo extends JpaRepository<Post,Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByTitleContaining(String keyword);
}
