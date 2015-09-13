package com.asos.wojtek.myasosclient.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Serializable {

    private String description;
    private List<CategoryDetails> listing = new ArrayList<CategoryDetails>();

    public Categories(String description, List<CategoryDetails> listing) {
        this.description = description;
        this.listing = listing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CategoryDetails> getListing() {
        return listing;
    }

    public void setListing(List<CategoryDetails> listing) {
        this.listing = listing;
    }



}
