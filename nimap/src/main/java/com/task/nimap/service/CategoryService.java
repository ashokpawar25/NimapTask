package com.task.nimap.service;

import com.task.nimap.controller.exception.CategoryAlreadyExistsException;
import com.task.nimap.dao.CategoryRepository;
import com.task.nimap.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    public Category save(Category category) throws CategoryAlreadyExistsException {

        Category existingCategory = categoryRepository.findByName(category.getName());
        if(existingCategory != null)
        {
            throw new CategoryAlreadyExistsException("Category with this name is already present");
        }
        return categoryRepository.save(category);
    }
}
