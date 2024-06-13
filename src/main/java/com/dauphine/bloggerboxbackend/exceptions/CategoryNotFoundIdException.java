package com.dauphine.bloggerboxbackend.exceptions;
import java.util.UUID;
public class CategoryNotFoundIdException extends Exception{
    public CategoryNotFoundIdException(UUID categoryId) {
        super("The category with id " + categoryId + " was not found.");
    }
}



