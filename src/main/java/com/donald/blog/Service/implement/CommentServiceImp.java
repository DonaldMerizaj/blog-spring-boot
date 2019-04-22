package com.donald.blog.Service.implement;

import com.donald.blog.Models.Comment;
import com.donald.blog.Models.User;
import com.donald.blog.Models.dto.CommentDto;
import com.donald.blog.Repository.CommentRepository;
import com.donald.blog.Service.CommentService;
import com.donald.blog.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setPost(commentDto.getPost());
        comment.setUser(commentDto.getUser());
//        comment.setCreatedAt(new Date());
        comment.setStatus(1);
        return commentRepository.saveAndFlush(comment);
    }
}
