package com.management.blogwebsite.controller;

import com.management.blogwebsite.dto.CommentDto;
import com.management.blogwebsite.dto.PostDto;
import com.management.blogwebsite.dto.RegistrationDto;
import com.management.blogwebsite.entity.Comment;
import com.management.blogwebsite.entity.User;
import com.management.blogwebsite.repository.UserRepository;
import com.management.blogwebsite.service.CommentService;
import com.management.blogwebsite.service.PostService;
import com.management.blogwebsite.service.UserService;
import com.management.blogwebsite.util.ROLE;
import com.management.blogwebsite.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;
    private UserService userService;

    public PostController(PostService postService, CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    // Handler Methoden GET Request und ModelVIew als Rückgabe
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        // Hier den angemeldeten Benutzernamen abrufen
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String role = SecurityUtils.getRole();
        List<PostDto> posts = null;
        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            posts = postService.findAllPosts();
        } else {
            posts = postService.findPostByUser();
        }
        model.addAttribute("username", username);
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    //    //Handler Methode: Liste von Kommentaren im REQUEST
//    @GetMapping("/admin/posts/comments")
//    public String postComments(Model model) {
//        List<CommentDto> comments = commentService.findAllComment();
//        model.addAttribute("comments", comments);
//        return "/admin/comments";
//    }
//Handler Methode: Liste von Kommentaren im REQUEST
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model) {
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;
        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            comments = commentService.findAllComment();
        } else {
            comments = commentService.findCommentsByPost();
        }
        model.addAttribute("comments", comments);
        return "/admin/comments";
    }

    //Handler Methode: Kommentar Löschen im REQUEST

    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComments(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

    //Handler Methode um newposts Request zu verarbeiten. Anfangs wird ein leeres PostDto erstellt
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create_post";
    }

    // Post abschicken Request bearbeiten als Handler Methode
    // @ModelAttribute dient dazu die Form Daten zu lesen und die th: Field Values am Model Objekt anzupassen
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    // Handler Methode: die URL /admin/posts/{postId}/edit Bearbeitungsfunktion nachdem man Bearbeiten Button klickt
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long id
            , Model model) {
        PostDto postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    // handler Methode für das Editieren von Posts
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable Long postId,
                             @Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);

        return "redirect:/admin/posts";
    }

    //Handler Methode um Post ids zu löschen post Request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    //Handler Methode: Beitrag Details anschauen/viewen
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable String postUrl,
                           Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }


    //Handler Methode: SuchFunktion durch Beiträge
    //localhost::8808/admin/posts/search?query=keyword
    // query muss als name="query" im input stehen
    @GetMapping("/admin/posts/search")
    public String searchPosts(String query, Model model) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }

    // bekomme die URL, indem man diese to lowercases und statt Spaces(Leerzeichen) mit BindeStrichen versieht
    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }


}
