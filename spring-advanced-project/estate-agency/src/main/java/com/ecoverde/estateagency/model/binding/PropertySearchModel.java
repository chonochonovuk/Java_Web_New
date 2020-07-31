package com.ecoverde.estateagency.model.binding;

import com.ecoverde.estateagency.model.entity.PropertyType;

import java.math.BigDecimal;

public class PropertySearchModel {
    private String keyword;
    private PropertyType propertyType;
    private String location;
    private BigDecimal price;

    public PropertySearchModel() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
