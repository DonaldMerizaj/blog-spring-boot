package com.donald.blog.Service;


import com.donald.blog.Models.Comment;
import com.donald.blog.Models.dto.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Comment save(CommentDto comment);
}
