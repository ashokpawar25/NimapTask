package com.task.nimap.controller;

import com.task.nimap.controller.exception.CategoryAlreadyExistsException;
import com.task.nimap.entity.Category;
import com.task.nimap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody Category category) throws CategoryAlreadyExistsException {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
