package com.asos.wojtek.myasosclient.network;

import com.asos.wojtek.myasosclient.data.json.CategoriesJSON;
import com.asos.wojtek.myasosclient.data.json.ProductDetailsJSON;
import com.asos.wojtek.myasosclient.data.json.ProductsListingJSON;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface AsosApiInterface {

    @GET("/cats_women.json")
    void getWomenCategories(Callback<CategoriesJSON> cb);

    @GET("/cats_men.json")
    void getMenCategories(Callback<CategoriesJSON> cb);

    @GET("/anyproduct_details.json")
    void getProductDetails(@Query("prodid") final String productId, Callback<ProductDetailsJSON> cb);

    @GET("/anycat_products.json")
    void getProductsInCategory(@Query("catid") final String catId, Callback<ProductsListingJSON> cb);
}
