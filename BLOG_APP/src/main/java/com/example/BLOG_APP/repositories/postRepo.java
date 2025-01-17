package com.example.BLOG_APP.repositories;

import com.example.BLOG_APP.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postRepo extends JpaRepository<Post,Integer> {
    //Rem
}
