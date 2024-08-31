package com.task.nimap.controller;

import com.task.nimap.dto.ProductRequestDto;
import com.task.nimap.dto.ProductResponseDto;
import com.task.nimap.entity.Product;
import com.task.nimap.service.ProductService;
import com.task.nimap.service.exception.CategoryNotFoundException;
import com.task.nimap.service.exception.ProductAlreadyExistsException;
import com.task.nimap.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page", defaultValue = "0", required = false) int page) {
        final int DEFAULT_PAGE_SIZE = 2;
        return ResponseEntity.ok(productService.getAllProducts(page, DEFAULT_PAGE_SIZE));
    }

    @PostMapping
    ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) throws ProductAlreadyExistsException, CategoryNotFoundException {
        return ResponseEntity.ok(productService.saveProduct(requestDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) throws ProductAlreadyExistsException, CategoryNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id, requestDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with id:" + id + " is deleted successfully");
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
