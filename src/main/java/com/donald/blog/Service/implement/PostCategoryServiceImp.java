package com.donald.blog.Service.implement;

import com.donald.blog.Models.PostCategory;
import com.donald.blog.Repository.PostCategoryRepository;
import com.donald.blog.Repository.PostRepository;
import com.donald.blog.Service.PostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCategoryServiceImp implements PostCategoryService {

    PostCategoryRepository postCategoryRepository;

    @Autowired
    public PostCategoryServiceImp(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }


    @Override
    public PostCategory findByName(String name) {

        return postCategoryRepository.findByName(name);
    }

    @Override
    public Optional<PostCategory> findById(int id) {
        return postCategoryRepository.findById(id);
    }
}
