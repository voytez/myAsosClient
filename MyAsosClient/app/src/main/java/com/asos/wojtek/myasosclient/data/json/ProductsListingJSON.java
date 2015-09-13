package com.asos.wojtek.myasosclient.data.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsListingJSON {

    private List<Object> AlsoSearched = new ArrayList<Object>();
    private String Description;
    private List<FacetJSON> Facets = new ArrayList<FacetJSON>();
    private Integer ItemCount;
    private List<ListingJSON> Listings = new ArrayList<ListingJSON>();
    private String RedirectUrl;
    private String SortType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The AlsoSearched
     */
    public List<Object> getAlsoSearched() {
        return AlsoSearched;
    }

    /**
     * @param AlsoSearched The AlsoSearched
     */
    public void setAlsoSearched(List<Object> AlsoSearched) {
        this.AlsoSearched = AlsoSearched;
    }

    /**
     * @return The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return The Facets
     */
    public List<FacetJSON> getFacets() {
        return Facets;
    }

    /**
     * @param Facets The Facets
     */
    public void setFacets(List<FacetJSON> Facets) {
        this.Facets = Facets;
    }

    /**
     * @return The ItemCount
     */
    public Integer getItemCount() {
        return ItemCount;
    }

    /**
     * @param ItemCount The ItemCount
     */
    public void setItemCount(Integer ItemCount) {
        this.ItemCount = ItemCount;
    }

    /**
     * @return The Listings
     */
    public List<ListingJSON> getListings() {
        return Listings;
    }

    /**
     * @param Listings The Listings
     */
    public void setListings(List<ListingJSON> Listings) {
        this.Listings = Listings;
    }

    /**
     * @return The RedirectUrl
     */
    public String getRedirectUrl() {
        return RedirectUrl;
    }

    /**
     * @param RedirectUrl The RedirectUrl
     */
    public void setRedirectUrl(String RedirectUrl) {
        this.RedirectUrl = RedirectUrl;
    }

    /**
     * @return The SortType
     */
    public String getSortType() {
        return SortType;
    }

    /**
     * @param SortType The SortType
     */
    public void setSortType(String SortType) {
        this.SortType = SortType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}