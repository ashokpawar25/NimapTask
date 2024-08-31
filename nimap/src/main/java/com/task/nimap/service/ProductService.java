package com.task.nimap.service;

import com.task.nimap.dao.CategoryRepository;
import com.task.nimap.dao.ProductRepository;
import com.task.nimap.dto.ProductRequestDto;
import com.task.nimap.dto.ProductResponseDto;
import com.task.nimap.entity.Category;
import com.task.nimap.entity.Product;
import com.task.nimap.service.exception.CategoryNotFoundException;
import com.task.nimap.service.exception.ProductAlreadyExistsException;
import com.task.nimap.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAllProducts(int page, int DEFAULT_PAGE_SIZE) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        return productRepository.findAll(pageRequest).getContent();
    }

    public ProductResponseDto saveProduct(ProductRequestDto requestDto) throws ProductAlreadyExistsException, CategoryNotFoundException {
        Product existingProduct = productRepository.findByName(requestDto.getName());
        if (existingProduct != null)
            throw new ProductAlreadyExistsException("Product with name " + requestDto.getName() + " is already present");

        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category with id:" + requestDto.getCategoryId() + " id not present"));

        Product product = new Product(requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(), category);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice(), savedProduct.getCategory().getId());
    }

    public Product findProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id:" + id + " is not present"));
    }

}