package com.asos.wojtek.myasosclient.data.model;

import java.util.List;

public class ProductDetails {

    private String name;
    private String price;
    private String productId;
    private List<String> imageUrls;
    private String brand;
    private String additionalInfo;
    private String careInfo;
    private String description;

    public ProductDetails(String name, String price, String productId, List<String> imageUrls, String brand, String additionalInfo, String careInfo, String description) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.imageUrls = imageUrls;
        this.brand = brand;
        this.additionalInfo = additionalInfo;
        this.careInfo = careInfo;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCareInfo() {
        return careInfo;
    }

    public void setCareInfo(String careInfo) {
        this.careInfo = careInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
