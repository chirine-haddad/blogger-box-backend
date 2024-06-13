package com.dauphine.bloggerboxbackend.exceptions;

public class CategoryAlreadyExistsException extends Exception {
    public CategoryAlreadyExistsException (String categoryName) {
        super("The category " + categoryName + " already exists.");
    }
}