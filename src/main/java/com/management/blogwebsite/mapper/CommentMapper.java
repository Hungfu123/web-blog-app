package com.management.blogwebsite.mapper;

import com.management.blogwebsite.dto.CommentDto;
import com.management.blogwebsite.entity.Comment;

public class CommentMapper {

    public static CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .createdOn(comment.getCreatedOn())
                .content(comment.getContent())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }

    public static Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .createdOn(commentDto.getCreatedOn())
                .content(commentDto.getContent())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }
}
