package com.dauphine.bloggerboxbackend.services.impl;

import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.repositories.PostRepository;
import com.dauphine.bloggerboxbackend.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.getAllByCategoryId(categoryId);
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, Category category) {
        Post post = new Post(title, content,category);


        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);

            return repository.save(post);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
