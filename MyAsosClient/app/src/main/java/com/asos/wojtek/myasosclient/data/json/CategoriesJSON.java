package com.asos.wojtek.myasosclient.data.json;

import java.util.ArrayList;
import java.util.List;


public class CategoriesJSON {

    private String Description;
    private List<CategoryDetailsJSON> Listing = new ArrayList<CategoryDetailsJSON>();
    private String SortType;

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The Listing
     */
    public List<CategoryDetailsJSON> getListing() {
        return Listing;
    }

    /**
     *
     * @param Listing
     * The Listing
     */
    public void setListing(List<CategoryDetailsJSON> Listing) {
        this.Listing = Listing;
    }

    /**
     *
     * @return
     * The SortType
     */
    public String getSortType() {
        return SortType;
    }

    /**
     *
     * @param SortType
     * The SortType
     */
    public void setSortType(String SortType) {
        this.SortType = SortType;
    }

    public class CategoryDetailsJSON {

        private String CategoryId;
        private String Name;
        private Integer ProductCount;

        /**
         *
         * @return
         * The CategoryId
         */
        public String getCategoryId() {
            return CategoryId;
        }

        /**
         *
         * @param CategoryId
         * The CategoryId
         */
        public void setCategoryId(String CategoryId) {
            this.CategoryId = CategoryId;
        }

        /**
         *
         * @return
         * The Name
         */
        public String getName() {
            return Name;
        }

        /**
         *
         * @param Name
         * The Name
         */
        public void setName(String Name) {
            this.Name = Name;
        }

        /**
         *
         * @return
         * The ProductCount
         */
        public Integer getProductCount() {
            return ProductCount;
        }

        /**
         *
         * @param ProductCount
         * The ProductCount
         */
        public void setProductCount(Integer ProductCount) {
            this.ProductCount = ProductCount;
        }

    }

}


