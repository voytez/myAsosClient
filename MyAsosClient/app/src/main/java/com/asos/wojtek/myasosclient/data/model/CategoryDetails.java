package com.asos.wojtek.myasosclient.data.model;

import java.io.Serializable;

public class CategoryDetails implements Serializable {

    private String categoryId;
    private String name;
    private Integer productCount;


    public CategoryDetails(String categoryId, String name, Integer productCount) {
        this.categoryId = categoryId;
        this.name = name;
        this.productCount = productCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

}
