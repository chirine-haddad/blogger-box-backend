package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.models.Post;
import com.dauphine.bloggerboxbackend.models.Category;
import java.util.List;
import java.util.UUID;
import com.dauphine.bloggerboxbackend.exceptions.*;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundIdException;
    Post getById(UUID id) throws PostNotFoundByIdException;
    Post create(String title, String content, Category category) throws CategoryNotFoundIdException;
    Post update(UUID id, String title, String content) throws PostNotFoundByIdException, CategoryNotFoundIdException;
    boolean deleteById(UUID id)  throws PostNotFoundByIdException;
   /* List<Post> getAllByTitleOrContent(String keyword);  */
}
