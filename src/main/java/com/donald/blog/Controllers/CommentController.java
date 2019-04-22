package com.donald.blog.Controllers;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.User;
import com.donald.blog.Models.dto.CommentDto;
import com.donald.blog.Service.CommentService;
import com.donald.blog.Service.PostService;
import com.donald.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping(value = "/createComment")
    public String createNewPost(@ModelAttribute("commentDto") @Valid CommentDto comment,
                                @RequestParam("id_post") int postId,
                                Principal principal,
                                BindingResult bindingResult) {

        Optional<Post> post = postService.findForId(postId);

        post.ifPresent(comment::setPost);

        Optional<User> user = userService.findByUsername(principal.getName());

        user.ifPresent(comment::setUser);

        if (!bindingResult.hasErrors()) {
            commentService.save(comment);
        }

        return "redirect:/post/" + comment.getPost().getId();

    }



}
