package com.exam.springexam.init;

import com.exam.springexam.service.CategoryService;
import com.exam.springexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public DataInit(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        this.productService.findAllProductsByCategory();
    }
}
