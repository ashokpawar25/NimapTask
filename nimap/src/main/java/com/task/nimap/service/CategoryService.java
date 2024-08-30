package com.task.nimap.service;

import com.task.nimap.controller.exception.CategoryAlreadyExistsException;
import com.task.nimap.dao.CategoryRepository;
import com.task.nimap.entity.Category;
import com.task.nimap.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category save(Category category) throws CategoryAlreadyExistsException {

        Category existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory != null) {
            throw new CategoryAlreadyExistsException("Category with "+category.getName()+" name is already present");
        }
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        return existingCategory.orElseThrow(()-> new CategoryNotFoundException("category with id:"+id+" is not present"));
    }
}
