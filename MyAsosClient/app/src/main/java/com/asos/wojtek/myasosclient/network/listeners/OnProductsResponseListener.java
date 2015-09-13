package com.asos.wojtek.myasosclient.network.listeners;

import com.asos.wojtek.myasosclient.data.model.Categories;
import com.asos.wojtek.myasosclient.data.model.ProductsListing;

import retrofit.RetrofitError;


public interface OnProductsResponseListener {
    void onProductsListingSuccess(ProductsListing productsListing);

    void onResponseError(RetrofitError error);
}
