package com.examprep.springbasicexamprep.service.impl;

import com.examprep.springbasicexamprep.model.entity.Category;
import com.examprep.springbasicexamprep.model.entity.CategoryName;
import com.examprep.springbasicexamprep.model.service.CategoryServiceModel;
import com.examprep.springbasicexamprep.repository.CategoryRepository;
import com.examprep.springbasicexamprep.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCategories() {
       if (this.categoryRepository.count() == 0){
           Arrays.stream(CategoryName.values()).forEach(categoryName -> {
               Category category = new Category();
               category.setCategoryName(categoryName);
               category.setDescription(String.format("Description for %s",categoryName.name()));
               this.categoryRepository.save(category);});
       }
    }

    @Override
    public CategoryServiceModel findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                .map(category -> this.modelMapper.map(category,CategoryServiceModel.class)).orElse(null);
    }
}
