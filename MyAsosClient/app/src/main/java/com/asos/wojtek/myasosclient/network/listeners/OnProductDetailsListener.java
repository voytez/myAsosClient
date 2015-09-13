package com.asos.wojtek.myasosclient.network.listeners;

import com.asos.wojtek.myasosclient.data.model.ProductDetails;
import com.asos.wojtek.myasosclient.data.model.ProductsListing;

import retrofit.RetrofitError;


public interface OnProductDetailsListener {
    void onProductsDetailsSuccess(ProductDetails productDetails);

    void onResponseError(RetrofitError error);
}
