package com.management.blogwebsite.repository;

import com.management.blogwebsite.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    //SQL Statements werden erstellt
    Optional<Post> findByUrl(String url);

    // wird für ein SQL query genutzt wegen @Query durch Titel und KurzBeschreibung
    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%') ")
    List<Post> searchPosts(String query);

    @Query(value = "SELECT * FROM POSTS p WHERE p.created_by =:userId", nativeQuery = true)
    List<Post> findPostByUser(Long userId);

}
