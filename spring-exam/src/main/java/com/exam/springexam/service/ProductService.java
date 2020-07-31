package com.exam.springexam.service;

import com.exam.springexam.model.entity.CategoryName;
import com.exam.springexam.model.service.ProductServiceModel;

import java.util.List;
import java.util.Map;

public interface ProductService {
  ProductServiceModel addProduct(ProductServiceModel productServiceModel);
  Map<String ,List<ProductServiceModel>> findAllProductsByCategory();
}
