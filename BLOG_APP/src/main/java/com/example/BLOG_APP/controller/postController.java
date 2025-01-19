package com.example.BLOG_APP.controller;

import com.example.BLOG_APP.payloads.PostDto;
import com.example.BLOG_APP.payloads.postResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.BLOG_APP.services.postService;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class postController {
    @Autowired
    private postService postService;

    //Create Post
    @PostMapping("/user/{user_id}/category/{category_id}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable Integer user_id, @PathVariable Integer category_id){
        PostDto postDto1=this.postService.createPost(postDto,user_id,category_id);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    //Get by users
    @GetMapping("user/{user_id}/posts")
    public ResponseEntity<List<PostDto>>getAllUserPosts( @PathVariable Integer user_id){
        List<PostDto>newList=this.postService.getAllPostsByUserId(user_id);
        return new ResponseEntity<List<PostDto>>(newList,HttpStatus.OK);

    }

    // Get All Posts by Category
    @GetMapping("category/{category_id}/posts")
    public ResponseEntity<List<PostDto>> getAllCategoryPosts(@PathVariable Integer category_id) {
        List<PostDto> newList = this.postService.getAllPostsByCategoryId(category_id);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    // Update Post
    @PutMapping("/posts/{post_id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer post_id) {
        PostDto updatedPost = this.postService.updatePost(postDto, post_id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Delete Post
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer post_id) {
        this.postService.deletePost(post_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<postResponse> getAllPosts(@RequestParam(value = "page_number", defaultValue = "0", required = false) Integer page_number,
                                                    @RequestParam(value = "page_size", defaultValue = "10", required = false) Integer page_size,
                                                    @RequestParam(value = "sort_by", defaultValue = "post_id", required = false) String sort_by) {
        postResponse postResponse = this.postService.getAllPosts(page_number,page_size,sort_by);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // Search Posts
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
        List<PostDto> searchResults = this.postService.searchPost(keyword);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
    //Update put


    //Delete


}
