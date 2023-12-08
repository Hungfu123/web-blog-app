package com.management.blogwebsite.service.impl;

import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.entity.Post;
import com.management.blogwebsite.entity.User;
import com.management.blogwebsite.mapper.PostMapper;
import com.management.blogwebsite.repository.PostRepository;
import com.management.blogwebsite.repository.UserRepository;
import com.management.blogwebsite.service.PostService;
import com.management.blogwebsite.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository= userRepository;
    }


    // hier werden die Posts Objekte in PostDto umgewandelt um diese dann alle zu finden und die in der Liste zusammenzufassen
    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostByUser() {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts= postRepository.findPostByUser(userId);
        return posts.stream()
                .map(post -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    // PostDto in Post umgewandelt und es wird dann in der DB gespeichert
    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postid) {
        Post post = postRepository.findById(postid).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);

    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
                .map(post -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }
}
