package com.donald.blog.Models.dto;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.User;

import javax.validation.constraints.NotEmpty;

public class CommentDto {

    @NotEmpty
    private String comment;

    private Post post;

    private User user;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
