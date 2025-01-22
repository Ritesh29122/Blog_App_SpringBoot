package com.example.BLOG_APP.controller;

import com.example.BLOG_APP.payloads.commentDto;
import com.example.BLOG_APP.services.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class commentController {

    @Autowired
    private commentService commentService;

    // Create a comment
    @PostMapping("/post/{post_id}/user/{user_id}")
    public ResponseEntity<commentDto> createComment(
            @RequestBody commentDto commentDto,
            @PathVariable Integer post_id,
            @PathVariable Integer user_id) {
        commentDto createdComment = this.commentService.createComment(commentDto, post_id, user_id);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Delete a comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        this.commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
