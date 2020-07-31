package com.exam.springexam.service;

import com.exam.springexam.model.entity.CategoryName;
import com.exam.springexam.model.entity.Product;
import com.exam.springexam.model.service.CategoryServiceModel;
import com.exam.springexam.model.service.ProductServiceModel;
import com.exam.springexam.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel,Product.class);
        this.productRepository.saveAndFlush(product);
        return this.modelMapper.map(product,ProductServiceModel.class);
    }

    @Override
    public Map<String, List<ProductServiceModel>> findAllProductsByCategory() {
        Map<String, List<ProductServiceModel>> categorized = new HashMap<>();
        for (CategoryName value : CategoryName.values()) {
            List<ProductServiceModel> products = this.findByCategoryName(value.name());
            if(!products.isEmpty()){
                categorized.put(value.name().toLowerCase(),products);
            }
        }

        return categorized;

    }

    public List<ProductServiceModel> findByCategoryName(String name){
        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().getCategoryName().name().equals(name))
                .map(product -> this.modelMapper.map(product,ProductServiceModel.class))
                .collect(Collectors.toList());
    }

}
