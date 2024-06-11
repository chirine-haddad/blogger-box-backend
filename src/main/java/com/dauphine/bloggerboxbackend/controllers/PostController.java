package com.dauphine.bloggerboxbackend.controllers;


import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.dto.CreationPostRequest;
import com.dauphine.bloggerboxbackend.dto.UpdatePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post", description = "Endpoints for managing posts")
public class PostController {
    private final List<Post> temporaryPosts;

    public PostController() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "Content of the first post", LocalDateTime.now(), UUID.randomUUID()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second Post", "Content of the second post", LocalDateTime.now(), UUID.randomUUID()));
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest postRequest) {
        Post post = new Post(UUID.randomUUID(), postRequest.getTitle(), postRequest.getContent(), LocalDateTime.now(), postRequest.getCategoryId());
        temporaryPosts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest postRequest) {
        for (Post post : temporaryPosts) {
            if (post.getId().equals(id)) {
                post.setTitle(postRequest.getTitle());
                post.setContent(postRequest.getContent());
                post.setCategoryId(postRequest.getCategoryId());
                return post;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void deletePost(@PathVariable UUID id) {
        temporaryPosts.removeIf(post -> post.getId().equals(id));
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        return temporaryPosts.stream().sorted((p1, p2) -> p2.getCreatedDate().compareTo(p1.getCreatedDate())).collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts for a specific category")
    public List<Post> retrievePostsByCategory(@PathVariable UUID categoryId) {
        return temporaryPosts.stream().filter(post -> post.getCategoryId().equals(categoryId)).collect(Collectors.toList());
    }
}
