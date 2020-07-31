package com.exam.springexam.service;

import com.exam.springexam.model.entity.Category;
import com.exam.springexam.model.entity.CategoryName;
import com.exam.springexam.model.service.CategoryServiceModel;
import com.exam.springexam.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService{
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
                category.setDescription(String.format("Description for product of category type - %s",categoryName.name()));
                this.categoryRepository.save(category);});
        }
    }

    @Override
    public CategoryServiceModel findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                .map(category -> this.modelMapper.map(category,CategoryServiceModel.class)).orElse(null);
    }
}
