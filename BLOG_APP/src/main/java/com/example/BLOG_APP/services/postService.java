package com.example.BLOG_APP.services;

import com.example.BLOG_APP.models.Post;
import com.example.BLOG_APP.payloads.CategoryDto;
import com.example.BLOG_APP.payloads.PostDto;
import com.example.BLOG_APP.payloads.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface postService {
    Post createPost(PostDto postDto,Integer user_id, Integer category_id);

    Post updatePost(PostDto postDto, Integer post_id);

    void deletePost(Integer post_id);

    List<Post>getAllPosts();


//    List<Post>getAllPostsByCategory(CategoryDto categoryDto);
//
//    List<Post>getAllPostsByUser(UserDto userDto);

    Post getAllPostsByCategoryId(Integer category_id);

    Post getAllPostsByUserId(Integer user_id);

    List<Post>searchPost(String keyword);
}
