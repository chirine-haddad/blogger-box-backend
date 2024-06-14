package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.dto.CreationPostRequest;
import com.dauphine.bloggerboxbackend.dto.UpdatePostRequest;
import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.services.PostService;
import com.dauphine.bloggerboxbackend.exceptions.PostNotFoundByIdException;
import com.dauphine.bloggerboxbackend.exceptions.CategoryNotFoundIdException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Post> createPost(@RequestBody CreationPostRequest postRequest) {
        try {
            Post createdPost = postService.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();   }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest postRequest) {
        try {
            Post updatedPost = postService.update(id, postRequest.getTitle(), postRequest.getContent());
            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (PostNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        try {
            boolean deleted = postService.deleteById(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (PostNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts for a specific category")
    public ResponseEntity<List<Post>> retrievePostsByCategory(@PathVariable UUID categoryId) {
        try {
            List<Post> posts = postService.getAllByCategoryId(categoryId);
            return ResponseEntity.ok(posts);
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a post by ID")
    public ResponseEntity<Post> retrievePostById(@PathVariable UUID id) {
        try {
            Post post = postService.getById(id);
            if (post != null) {
                return ResponseEntity.ok(post);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (PostNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}