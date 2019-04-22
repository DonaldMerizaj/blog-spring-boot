package com.donald.blog.Repository;

import com.donald.blog.Models.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    PostCategory findByName(@Param("name") String name);
    Optional<PostCategory> findById(@Param("id") int id);
}