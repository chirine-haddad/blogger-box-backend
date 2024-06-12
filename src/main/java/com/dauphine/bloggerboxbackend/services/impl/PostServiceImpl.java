package com.dauphine.bloggerboxbackend.services.impl;

import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "This is the content of the first post", LocalDateTime.now(), UUID.randomUUID()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second Post", "This is the content of the second post", LocalDateTime.now(), UUID.randomUUID()));
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> post.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, LocalDateTime.now(), categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setCategoryId(categoryId);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(post -> post.getId().equals(id));
    }
}
