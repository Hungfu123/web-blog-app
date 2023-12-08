package com.management.blogwebsite.service.impl;

import com.management.blogwebsite.dto.CommentDto;
import com.management.blogwebsite.entity.Comment;
import com.management.blogwebsite.entity.Post;
import com.management.blogwebsite.entity.User;
import com.management.blogwebsite.mapper.CommentMapper;
import com.management.blogwebsite.repository.CommentRepository;
import com.management.blogwebsite.repository.PostRepository;
import com.management.blogwebsite.repository.UserRepository;
import com.management.blogwebsite.service.CommentService;
import com.management.blogwebsite.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        //Kommentar auf diesen Post setzen wegen der ManyToOne Beziehung
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComment() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);

        return comments.stream()
                .map(comment -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }
}
