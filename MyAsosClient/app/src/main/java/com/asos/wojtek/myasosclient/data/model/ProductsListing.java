package com.asos.wojtek.myasosclient.data.model;

import com.asos.wojtek.myasosclient.data.json.FacetJSON;
import com.asos.wojtek.myasosclient.data.json.ListingJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsListing {

    private String description;
    private int itemCount;
    private List<ProductItem> productsListing;

    public ProductsListing(String description, int itemCount, List<ProductItem> listing) {

        this.description = description;
        this.itemCount = itemCount;
        this.productsListing = listing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<ProductItem> getProductsListing() {
        return productsListing;
    }

    public void setProductsListing(List<ProductItem> productsListing) {
        this.productsListing = productsListing;
    }
}