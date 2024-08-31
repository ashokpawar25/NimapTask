package com.task.nimap.service;

import com.task.nimap.controller.exception.CategoryAlreadyExistsException;
import com.task.nimap.dao.CategoryRepository;
import com.task.nimap.entity.Category;
import com.task.nimap.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category save(Category category) throws CategoryAlreadyExistsException {

        Category existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory != null) {
            throw new CategoryAlreadyExistsException("Category with " + category.getName() + " name is already present");
        }
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        return existingCategory.orElseThrow(() -> new CategoryNotFoundException("Category with id:" + id + " is not present"));
    }

    public List<Category> findAllCategories(int page, int DEFAULT_PAGE_SIZE) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        return categoryRepository.findAll(pageRequest).getContent();
    }

    public Category updateCategory(Long id, String newCategoryName) throws CategoryNotFoundException, CategoryAlreadyExistsException {
        Category existingCategoryWithName = categoryRepository.findByName(newCategoryName);
        if (existingCategoryWithName != null)
            throw new CategoryAlreadyExistsException("Category with " + newCategoryName + " name is already present");

        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id:" + id + " is not present"));
        category.setName(newCategoryName);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id:" + id + " is not present"));
        categoryRepository.delete(category);
    }
}
