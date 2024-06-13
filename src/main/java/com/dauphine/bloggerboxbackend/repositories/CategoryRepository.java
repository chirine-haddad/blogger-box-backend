package com.dauphine.bloggerboxbackend.repositories;

import com.dauphine.bloggerboxbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("""
        SELECT c
        FROM Category c
        WHERE c.name LIKE CONCAT ('%', :name, '%')
    """)
    List<Category> findAllByName(@Param("name") String name);
}
