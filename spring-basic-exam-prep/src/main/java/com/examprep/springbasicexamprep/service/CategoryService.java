package com.examprep.springbasicexamprep.service;

import com.examprep.springbasicexamprep.model.entity.CategoryName;
import com.examprep.springbasicexamprep.model.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();
    CategoryServiceModel findByCategoryName(CategoryName categoryName);
}
