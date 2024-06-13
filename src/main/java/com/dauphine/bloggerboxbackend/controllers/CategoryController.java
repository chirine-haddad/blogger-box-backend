package com.dauphine.bloggerboxbackend.controllers;


import com.dauphine.bloggerboxbackend.dto.CreationCategoryRequest;
import com.dauphine.bloggerboxbackend.dto.UpdateCategoryRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(
        name = "Category Controller", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest request) {
        return service.create(request.getName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category name")
    public Category updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        return service.updateName(id, request.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public void deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Retrieve all categories by name")  // Nouveau endpoint
    public List<Category> retrieveCategoriesByName(@PathVariable String name) {
        return service.getAllByName(name);
    }
}