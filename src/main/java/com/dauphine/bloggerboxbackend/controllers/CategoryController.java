package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.dto.CreationCategoryRequest;
import com.dauphine.bloggerboxbackend.dto.UpdateCategoryRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.services.CategoryService;
import com.dauphine.bloggerboxbackend.exceptions.CategoryAlreadyExistsException;
import com.dauphine.bloggerboxbackend.exceptions.CategoryNotFoundIdException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category Controller", description = "Endpoints for managing categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID")
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) {
        try {
            Category category = categoryService.getById(id);
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> createCategory(@RequestBody CreationCategoryRequest request) {
        try {
            Category createdCategory = categoryService.create(request.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category name")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        try {
            Category updatedCategory = categoryService.updateName(id, request.getName());
            if (updatedCategory != null) {
                return ResponseEntity.ok(updatedCategory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Retrieve all categories by name")
    public List<Category> retrieveCategoriesByName(@PathVariable String name) {
        return categoryService.getAllByName(name);
    }
}