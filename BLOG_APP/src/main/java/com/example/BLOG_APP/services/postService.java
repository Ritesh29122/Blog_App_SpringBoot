package com.example.BLOG_APP.services;

import com.example.BLOG_APP.models.Post;
import com.example.BLOG_APP.payloads.CategoryDto;
import com.example.BLOG_APP.payloads.PostDto;
import com.example.BLOG_APP.payloads.UserDto;
import com.example.BLOG_APP.payloads.postResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface postService {
    PostDto createPost(PostDto postDto,Integer user_id, Integer category_id);

    PostDto updatePost(PostDto postDto, Integer post_id);

    void deletePost(Integer post_id);

    postResponse getAllPosts(Integer page_number, Integer page_size);


//    List<Post>getAllPostsByCategory(CategoryDto categoryDto);
//
//    List<Post>getAllPostsByUser(UserDto userDto);

    List<PostDto>getAllPostsByCategoryId(Integer category_id);

    List<PostDto>getAllPostsByUserId(Integer user_id);

    List<PostDto>searchPost(String keyword);
}
