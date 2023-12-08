package com.management.blogwebsite.repository;

import com.management.blogwebsite.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c.* FROM comments c inner JOIN posts p WHERE c.post_id = p.id and p.created_by =:userId", nativeQuery = true)
    List<Comment> findCommentsByPost(Long userId);
}
