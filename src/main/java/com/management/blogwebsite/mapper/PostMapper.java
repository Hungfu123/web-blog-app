package com.management.blogwebsite.mapper;

import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    // builder methoden wurden gemacht um die Sachen zu konverten weil sie auch dieselben Parameter haben
    // map Post Entity zu PostDto
    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream()
                        .map(comment -> CommentMapper.mapToCommentDto(comment))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
