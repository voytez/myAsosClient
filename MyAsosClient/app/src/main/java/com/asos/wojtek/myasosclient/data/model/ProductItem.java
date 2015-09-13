package com.asos.wojtek.myasosclient.data.model;

import java.util.List;

public class ProductItem {

    private String productId;
    private String productImageUrl;
    private String productPrice;
    private String productName;

    public ProductItem(String productId, String productName, String currentPrice, String productImageUrl) {

        this.productId = productId;
        this.productName = productName;
        this.productPrice = currentPrice;
        this.productImageUrl = productImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
