package com.management.blogwebsite.controller;

import com.management.blogwebsite.dto.CommentDto;
import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogController {

    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //Handler Methode: localhost:8080/ aufrufen
    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        List<PostDto> postsResponse = postService.findAllPosts();
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }

    //Handler Methode: view Post Request
    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable String postUrl, Model model, CommentDto comment) {
        PostDto post = postService.findPostByUrl(postUrl);
        model.addAttribute("post", post);
        model.addAttribute("comment", comment);
        return "blog/blog_post";
    }

    //Handler Methode: SuchFunktion durch Beiträge
    //localhost::8808/admin/posts/search?query=keyword
    // query muss als name="query" im input stehen
    @GetMapping("/page/search")
    public String searchPosts(String query, Model model) {
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }
}
