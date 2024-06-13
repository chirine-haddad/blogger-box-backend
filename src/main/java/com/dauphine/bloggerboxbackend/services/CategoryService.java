package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id);
    Category create(String name);
    Category updateName(UUID id, String name);
    boolean deleteById(UUID id);
    List<Category> getAllByName(String name);
}
