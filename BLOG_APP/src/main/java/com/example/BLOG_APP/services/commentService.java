package com.example.BLOG_APP.services;

import com.example.BLOG_APP.payloads.commentDto;

public interface commentService {

        commentDto createComment(commentDto commentDto,Integer post_id, Integer user_id);
        void deleteComment(Integer id);
}
