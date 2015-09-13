package com.asos.wojtek.myasosclient.data.model;


import com.asos.wojtek.myasosclient.data.json.CategoriesJSON;
import com.asos.wojtek.myasosclient.data.json.ListingJSON;
import com.asos.wojtek.myasosclient.data.json.ProductDetailsJSON;
import com.asos.wojtek.myasosclient.data.json.ProductsListingJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for transforming JSON objects into usable MODEL objects
 */
public class JsonToModelTransformHelper {

    public static Categories transformCategories(CategoriesJSON catsJson) {
        List<CategoryDetails> listing = new ArrayList<>();
        for (CategoriesJSON.CategoryDetailsJSON catDetails : catsJson.getListing()) {
            listing.add(new CategoryDetails(catDetails.getCategoryId(), catDetails.getName(), catDetails.getProductCount()));
        }
        return new Categories(catsJson.getDescription(), listing);
    }

    public static ProductsListing transformProductsListing(ProductsListingJSON productsJson) {
        List<ProductItem> listing = new ArrayList<>();
        for (ListingJSON listingItem : productsJson.getListings()) {
            listing.add(new ProductItem(String.valueOf(listingItem.getProductId()), listingItem.getTitle(), listingItem.getCurrentPrice(), listingItem.getProductImageUrl().get(0)));
        }
        return new ProductsListing(productsJson.getDescription(), productsJson.getItemCount(), listing);
    }

    public static ProductDetails transformProductDetails(ProductDetailsJSON pDetails) {
        List<String> imgUrlList = new ArrayList<>();
        for (String imgUrl : pDetails.getProductImageUrls()) {
            imgUrlList.add(imgUrl);
        }
        return new ProductDetails(pDetails.getTitle(), pDetails.getCurrentPrice(), String.valueOf(pDetails.getProductId()), imgUrlList, pDetails.getBrand(),
                pDetails.getAdditionalInfo(), pDetails.getCareInfo(), pDetails.getDescription());
    }
}
