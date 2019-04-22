package com.donald.blog.Controllers;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.PostCategory;
import com.donald.blog.Models.User;
import com.donald.blog.Models.dto.CommentDto;
import com.donald.blog.Models.dto.PostDto;
import com.donald.blog.Repository.PostCategoryRepository;
import com.donald.blog.Service.PostService;
import com.donald.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostCategoryRepository postCategoryRepository;

    @Autowired
    public PostController(PostService postService, UserService userService, PostCategoryRepository postCategory) {
        this.postService = postService;
        this.userService = userService;
        this.postCategoryRepository = postCategory;
    }

    @GetMapping(value = "/newPost")
    public String newPost(Principal principal,
                          Model model) {

        System.out.println(principal.getName());
        Optional<User> user = userService.findByUsername(principal.getName());

        if (user.isPresent()) {
            System.out.println(user.get().getName());

            PostDto postDto = new PostDto();
            postDto.setUser(user.get());

            List<PostCategory> categories = postCategoryRepository.findAll();

            model.addAttribute("postDto", postDto);
            model.addAttribute("categoryList", categories);

            return "/editPost";

        } else {
            return "/error";
        }
    }

    @PostMapping(value = "/newPost")
    public String newPost(@ModelAttribute("postDto") @Valid PostDto postDto,
                                Principal principal,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/editPost";
        } else {

            Optional<User> u = userService.findByUsername(principal.getName());
            u.ifPresent(postDto::setUser);
            Post post = postService.save(postDto);

            return "redirect:/blog/latest";
        }
    }

    @GetMapping(value = "/editPost/{id}")
    public String editPos(@PathVariable int id,
                                 Principal principal,
                                 Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setUser(post.getId_user());
            postDto.setTitle(post.getTitle());
            postDto.setContent(post.getContent());
            List<String> categories = new ArrayList<>();
            for (PostCategory category : post.getCategories()){
                categories.add(String.valueOf(category.getId()));
            }
            postDto.setCategories(categories);

            if (isPrincipalOwnerOfPost(principal, post)) {
                List<PostCategory> allCategories = postCategoryRepository.findAll();

                model.addAttribute("postDto", postDto);
                model.addAttribute("post", post);
                model.addAttribute("categoryList", allCategories);

                return "/editPost";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @GetMapping(value = "/post/{id}")
    public String getPost(@PathVariable int id,
                                Principal principal,
                                Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Optional<User> user = userService.findByUsername(post.getId_user().getUsername());
            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }

            if (user.isPresent()) {
                model.addAttribute("commentDto", new CommentDto());
                model.addAttribute("user", user.get());
            }

            return "/post";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable int id,
                                   Principal principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getId_user().getUsername());
    }
}
