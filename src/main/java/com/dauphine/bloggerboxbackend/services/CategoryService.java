package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.models.Category;

import com.dauphine.bloggerboxbackend.exceptions.*;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id) throws CategoryNotFoundIdException;;
    Category create(String name) throws CategoryAlreadyExistsException;
    Category updateName(UUID id, String name) throws CategoryNotFoundIdException, CategoryAlreadyExistsException;
    boolean deleteById(UUID id) throws CategoryNotFoundIdException;
    List<Category> getAllByName(String name);
}
