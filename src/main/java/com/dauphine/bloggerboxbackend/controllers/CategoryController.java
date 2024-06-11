package com.dauphine.bloggerboxbackend.controllers;



import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.dto.CreationCategoryRequest;
import com.dauphine.bloggerboxbackend.dto.UpdateCategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category", description = "Endpoints for managing categories")
public class CategoryController {
    private final List<Category> temporaryCategories = new ArrayList<>();

    public CategoryController() {

        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return temporaryCategories.stream().filter(cat -> cat.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest categoryRequest) {
        Category category = new Category(UUID.randomUUID(), categoryRequest.getName());
        temporaryCategories.add(category);
        return category;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category name")
    public Category updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest categoryRequest) {
        for (Category category : temporaryCategories) {
            if (category.getId().equals(id)) {
                category.setName(categoryRequest.getName());
                return category;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public void deleteCategory(@PathVariable UUID id) {
        temporaryCategories.removeIf(cat -> cat.getId().equals(id));
    }
}
