package com.exam.springexam.model.service;

import com.exam.springexam.model.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductServiceModel extends BaseServiceModel{
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private CategoryServiceModel categoryServiceModel;

    public ProductServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public CategoryServiceModel getCategoryServiceModel() {
        return categoryServiceModel;
    }

    public void setCategoryServiceModel(CategoryServiceModel categoryServiceModel) {
        this.categoryServiceModel = categoryServiceModel;
    }
}
