package com.donald.blog.Controllers;

import com.donald.blog.Models.Post;
import com.donald.blog.Service.PostService;
import com.donald.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    private final PostService postService;

    @Autowired
    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/blog/{category}")
    public String blogForCategory(@PathVariable String category,
                                          @RequestParam(defaultValue = "0") int page,
                                          Model model) {

        String categoryName = (category == null || category.isEmpty()) ? "all" : category;
        Page<Post> posts;

        if (categoryName.equals("all")){
             posts = postService.findAll(page);
        }else {
             posts = postService.findAllByCategoryOrderedByDatePageable(categoryName, page);
        }

        Pager pager = new Pager(posts);

        if (pager.indexOutOfBounds()){
            model.addAttribute("errorMessage", "No posts found !");

            return "/error";
        }
        model.addAttribute("pager", pager);
        model.addAttribute("category", categoryName);

        return "/posts";
    }

}
