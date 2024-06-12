package com.dauphine.bloggerboxbackend.services;


import com.dauphine.bloggerboxbackend.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByCategoryId(UUID categoryId);
    Post getById(UUID id);
    Post create(String title, String content, UUID categoryId);
    Post update(UUID id, String title, String content, UUID categoryId);
    boolean deleteById(UUID id);
}
