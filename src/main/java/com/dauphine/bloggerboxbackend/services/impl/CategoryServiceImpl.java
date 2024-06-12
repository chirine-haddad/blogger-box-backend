package com.dauphine.bloggerboxbackend.services.impl;

import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.repositories.CategoryRepository;
import com.dauphine.bloggerboxbackend.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);

        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return repository.save(category);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> getAllByName(String name) {  // Implémentation de la nouvelle méthode
        return repository.findAllByName(name);
    }
}
