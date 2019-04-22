package com.donald.blog.Service.implement;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.PostCategory;
import com.donald.blog.Models.dto.PostDto;
import com.donald.blog.Repository.PostCategoryRepository;
import com.donald.blog.Repository.PostRepository;
import com.donald.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostCategoryRepository categoryRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository, PostCategoryRepository category) {
        this.postRepository = postRepository;
        this.categoryRepository = category;
    }

    @Override
    public Optional<Post> findForId(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        List<PostCategory> categories = new ArrayList<>();

        for (String id : postDto.getCategories()){

            int idCategory = Integer.parseInt(id);

            Optional<PostCategory> postOpt = categoryRepository.findById(idCategory);
            postOpt.ifPresent(categories::add);
            post.setCategories(categories);

        }
        post.setId_user(postDto.getUser());
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> findAll(int page) {
        return postRepository.findAll(new PageRequest(subtractPageByOne(page), 5));
    }


    public List<Post> findAll() {
        return postRepository.findAll();
    }

//    @Override
//    public boolean hasCategory(int id_category) {
//        return postRepository.findById(id_category).isPresent();
//    }

    @Override
    public Page<Post> findAllOrderedByDatePageable(int page) {
        return postRepository.findAllByOrderByCreatedAtDesc(new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Post> findAllByCategoryOrderedByDatePageable(String category, int page) {

        return postRepository.findAllByCategoriesOrderByCreatedAt(categoryRepository.findByName(category),
                new PageRequest(subtractPageByOne(page), 5));

    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
