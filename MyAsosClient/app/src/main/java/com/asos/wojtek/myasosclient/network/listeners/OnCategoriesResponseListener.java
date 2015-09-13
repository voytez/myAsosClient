package com.asos.wojtek.myasosclient.network.listeners;

import com.asos.wojtek.myasosclient.data.json.CategoriesJSON;
import com.asos.wojtek.myasosclient.data.model.Categories;

import java.util.List;

import retrofit.RetrofitError;


public interface OnCategoriesResponseListener {
    void onWomenCategoriesSuccess(Categories categoriesListing);

    void onMenCategoriesSuccess(Categories categoriesListing);

    void onResponseError(RetrofitError error);
}
