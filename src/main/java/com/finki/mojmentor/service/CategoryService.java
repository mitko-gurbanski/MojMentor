package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryByCategoryName(String categoryName);
    List<Category> listAll();
}
