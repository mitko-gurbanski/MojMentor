package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Category;

public interface CategoryService {
    Category findCategoryByCategoryName(String categoryName);
}
