package com.exam.springexam.service;

import com.exam.springexam.model.entity.CategoryName;
import com.exam.springexam.model.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();
    CategoryServiceModel findByCategoryName(CategoryName categoryName);
}
