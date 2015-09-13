package com.asos.wojtek.myasosclient.data.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssociatedProductJSON {

    private Double BasePrice;
    private String Brand;
    private Object Colour;
    private String CurrentPrice;
    private Boolean InStock;
    private Boolean IsInSet;
    private String PreviousPrice;
    private String PriceType;
    private Integer ProductId;
    private List<String> ProductImageUrls = new ArrayList<String>();
    private String RRP;
    private Object Size;
    private Object Sku;
    private String Title;
    private String ProductType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The BasePrice
     */
    public Double getBasePrice() {
        return BasePrice;
    }

    /**
     *
     * @param BasePrice
     * The BasePrice
     */
    public void setBasePrice(Double BasePrice) {
        this.BasePrice = BasePrice;
    }

    /**
     *
     * @return
     * The Brand
     */
    public String getBrand() {
        return Brand;
    }

    /**
     *
     * @param Brand
     * The Brand
     */
    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    /**
     *
     * @return
     * The Colour
     */
    public Object getColour() {
        return Colour;
    }

    /**
     *
     * @param Colour
     * The Colour
     */
    public void setColour(Object Colour) {
        this.Colour = Colour;
    }

    /**
     *
     * @return
     * The CurrentPrice
     */
    public String getCurrentPrice() {
        return CurrentPrice;
    }

    /**
     *
     * @param CurrentPrice
     * The CurrentPrice
     */
    public void setCurrentPrice(String CurrentPrice) {
        this.CurrentPrice = CurrentPrice;
    }

    /**
     *
     * @return
     * The InStock
     */
    public Boolean getInStock() {
        return InStock;
    }

    /**
     *
     * @param InStock
     * The InStock
     */
    public void setInStock(Boolean InStock) {
        this.InStock = InStock;
    }

    /**
     *
     * @return
     * The IsInSet
     */
    public Boolean getIsInSet() {
        return IsInSet;
    }

    /**
     *
     * @param IsInSet
     * The IsInSet
     */
    public void setIsInSet(Boolean IsInSet) {
        this.IsInSet = IsInSet;
    }

    /**
     *
     * @return
     * The PreviousPrice
     */
    public String getPreviousPrice() {
        return PreviousPrice;
    }

    /**
     *
     * @param PreviousPrice
     * The PreviousPrice
     */
    public void setPreviousPrice(String PreviousPrice) {
        this.PreviousPrice = PreviousPrice;
    }

    /**
     *
     * @return
     * The PriceType
     */
    public String getPriceType() {
        return PriceType;
    }

    /**
     *
     * @param PriceType
     * The PriceType
     */
    public void setPriceType(String PriceType) {
        this.PriceType = PriceType;
    }

    /**
     *
     * @return
     * The ProductId
     */
    public Integer getProductId() {
        return ProductId;
    }

    /**
     *
     * @param ProductId
     * The ProductId
     */
    public void setProductId(Integer ProductId) {
        this.ProductId = ProductId;
    }

    /**
     *
     * @return
     * The ProductImageUrls
     */
    public List<String> getProductImageUrls() {
        return ProductImageUrls;
    }

    /**
     *
     * @param ProductImageUrls
     * The ProductImageUrls
     */
    public void setProductImageUrls(List<String> ProductImageUrls) {
        this.ProductImageUrls = ProductImageUrls;
    }

    /**
     *
     * @return
     * The RRP
     */
    public String getRRP() {
        return RRP;
    }

    /**
     *
     * @param RRP
     * The RRP
     */
    public void setRRP(String RRP) {
        this.RRP = RRP;
    }

    /**
     *
     * @return
     * The Size
     */
    public Object getSize() {
        return Size;
    }

    /**
     *
     * @param Size
     * The Size
     */
    public void setSize(Object Size) {
        this.Size = Size;
    }

    /**
     *
     * @return
     * The Sku
     */
    public Object getSku() {
        return Sku;
    }

    /**
     *
     * @param Sku
     * The Sku
     */
    public void setSku(Object Sku) {
        this.Sku = Sku;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The ProductType
     */
    public String getProductType() {
        return ProductType;
    }

    /**
     *
     * @param ProductType
     * The ProductType
     */
    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
