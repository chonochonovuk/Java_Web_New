package com.examprep.springbasicexamprep.model.binding;

import com.examprep.springbasicexamprep.model.entity.Category;
import com.examprep.springbasicexamprep.model.entity.CategoryName;
import com.examprep.springbasicexamprep.model.entity.GenderName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemAddBindingModel {
    private String name;
    private String description;
    private CategoryName category;
    private GenderName gender;
    private BigDecimal price;

    public ItemAddBindingModel() {
    }

    @Length(min = 2,message = "Mame length must be more than two characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3,message = "Description length must be more than three characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Enter valid category!")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    @NotNull(message = "Enter valid gender!")
    public GenderName getGender() {
        return gender;
    }

    public void setGender(GenderName gender) {
        this.gender = gender;
    }

    @DecimalMin(value = "0",message = "Price must be positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
