package com.example.BLOG_APP.services.Implementation;

import com.example.BLOG_APP.Exceptions.ResourceNotFoundException;
import com.example.BLOG_APP.models.Comment;
import com.example.BLOG_APP.models.Post;
import com.example.BLOG_APP.models.User;
import com.example.BLOG_APP.payloads.commentDto;
import com.example.BLOG_APP.repositories.UserRepo;
import com.example.BLOG_APP.services.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BLOG_APP.repositories.commentRepo;
import com.example.BLOG_APP.repositories.postRepo;


@Service
public class commentImpl implements commentService {

    @Autowired
    private commentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private postRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public commentDto createComment(commentDto commentDto, Integer post_id, Integer user_id) {

        Post post=this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post","post_id",post_id));
        User user=this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("user","user_id",user_id));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment comment1=this.commentRepo.save(comment);
        return this.modelMapper.map(comment1,commentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
        // Check if the comment exists, otherwise throw a ResourceNotFoundException
        Comment comment = this.commentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));

        // Delete the comment
        this.commentRepo.delete(comment);
    }
}
