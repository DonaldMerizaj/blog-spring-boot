package com.donald.blog.Service;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    Optional<Post> findForId(int id);

    Post save(PostDto post);

    Page<Post> findAll(int page);
    List<Post> findAll();

//    boolean hasCategory(int id_category);

    /**
     * Finds a {@link Page ) of all {@link Post} ordered by date
     */
    Page<Post> findAllOrderedByDatePageable(int page);

    /**
     * Finds a {@link Page ) of post by category {@link Post} ordered by date
     */
    Page<Post> findAllByCategoryOrderedByDatePageable(String category, int page);

    void delete(Post post);
}
