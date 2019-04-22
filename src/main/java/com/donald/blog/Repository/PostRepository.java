package com.donald.blog.Repository;

import com.donald.blog.Models.Post;
import com.donald.blog.Models.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByCategoriesOrderByCreatedAt(PostCategory category, Pageable pageable);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Optional<Post> findById(int id);
}
