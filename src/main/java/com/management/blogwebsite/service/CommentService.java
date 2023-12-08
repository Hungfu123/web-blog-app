package com.management.blogwebsite.service;

import com.management.blogwebsite.dto.CommentDto;


import java.util.List;


public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComment();
    void deleteComment(Long commentId);

    List<CommentDto> findCommentsByPost();
}
