package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.repository.CategoryRepository;
import com.finki.mojmentor.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByCategoryName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName).orElseThrow();
    }
}
