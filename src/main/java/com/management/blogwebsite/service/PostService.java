package com.management.blogwebsite.service;

import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.entity.User;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    List<PostDto> findPostByUser();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postid);
    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
