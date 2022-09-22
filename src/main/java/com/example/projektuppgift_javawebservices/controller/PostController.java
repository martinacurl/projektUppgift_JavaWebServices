package com.example.projektuppgift_javawebservices.controller;

import com.example.projektuppgift_javawebservices.dto.Post;
import com.example.projektuppgift_javawebservices.services.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}/posts")
    @PreAuthorize("@authService.idMatchesUserId(#id)")
    public List<Post> findAllByUserId(@PathVariable int id){
        return postService.findPostsByUserId(id);
    }
}
