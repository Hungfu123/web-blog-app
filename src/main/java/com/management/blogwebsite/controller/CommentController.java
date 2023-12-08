package com.management.blogwebsite.controller;

import com.management.blogwebsite.dto.CommentDto;
import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.service.CommentService;
import com.management.blogwebsite.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    //Handler Methode: erstelle Kommentar REQUEST
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult bindingResult, Model model) {
        PostDto postDto= postService.findPostByUrl(postUrl);
        if(bindingResult.hasErrors()){
            model.addAttribute("post", postDto);
            model.addAttribute("comment",commentDto);
            return "blog/blog_post";
        }
    commentService.createComment(postUrl, commentDto);
    return "redirect:/post/" + postUrl;
}

}
