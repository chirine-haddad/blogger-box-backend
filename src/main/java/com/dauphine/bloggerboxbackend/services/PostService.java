package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.models.Category;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByCategoryId(UUID categoryId);
    Post getById(UUID id);
    Post create(String title, String content, Category category);
    Post update(UUID id, String title, String content);
    boolean deleteById(UUID id);
   /* List<Post> getAllByTitleOrContent(String keyword);  */
}
