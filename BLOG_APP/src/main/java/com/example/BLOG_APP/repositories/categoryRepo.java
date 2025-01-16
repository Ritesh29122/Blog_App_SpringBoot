package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo extends JpaRepository<Category,Integer> {
}
