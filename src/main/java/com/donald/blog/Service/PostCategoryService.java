package com.donald.blog.Service;

import com.donald.blog.Models.PostCategory;

import java.util.Optional;

public interface PostCategoryService {
    PostCategory findByName(String name);
    Optional<PostCategory> findById(int id);
}
