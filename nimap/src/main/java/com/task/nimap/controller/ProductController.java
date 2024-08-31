package com.task.nimap.controller;

import com.task.nimap.dto.ProductRequestDto;
import com.task.nimap.dto.ProductResponseDto;
import com.task.nimap.service.ProductService;
import com.task.nimap.service.exception.CategoryNotFoundException;
import com.task.nimap.service.exception.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) throws ProductAlreadyExistsException, CategoryNotFoundException {
        return ResponseEntity.ok(productService.saveProduct(requestDto));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
