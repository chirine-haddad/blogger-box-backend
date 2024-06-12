package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.dto.CreationPostRequest;
import com.dauphine.bloggerboxbackend.dto.UpdatePostRequest;
import com.dauphine.bloggerboxbackend.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post", description = "Endpoints for managing posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest postRequest) {
        return postService.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest postRequest) {
        return postService.update(id, postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void deletePost(@PathVariable UUID id) {
        postService.deleteById(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts for a specific category")
    public List<Post> retrievePostsByCategory(@PathVariable UUID categoryId) {
        return postService.getAllByCategoryId(categoryId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a post by ID")
    public Post retrievePostById(@PathVariable UUID id) {
        return postService.getById(id);
    }
}
