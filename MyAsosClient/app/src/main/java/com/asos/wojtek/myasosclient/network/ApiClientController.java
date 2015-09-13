package com.asos.wojtek.myasosclient.network;

import com.asos.wojtek.myasosclient.data.json.CategoriesJSON;
import com.asos.wojtek.myasosclient.data.json.ProductDetailsJSON;
import com.asos.wojtek.myasosclient.data.json.ProductsListingJSON;
import com.asos.wojtek.myasosclient.data.model.Categories;
import com.asos.wojtek.myasosclient.data.model.JsonToModelTransformHelper;
import com.asos.wojtek.myasosclient.data.model.ProductDetails;
import com.asos.wojtek.myasosclient.data.model.ProductsListing;
import com.asos.wojtek.myasosclient.network.listeners.OnCategoriesResponseListener;
import com.asos.wojtek.myasosclient.network.listeners.OnProductDetailsListener;
import com.asos.wojtek.myasosclient.network.listeners.OnProductsResponseListener;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/* A Singleton instance of API client controller
 */
public class ApiClientController {

    private static ApiClientController apiClientController = new ApiClientController();

    private ApiClientController() {
    }

    public static ApiClientController getInstance() {
        return apiClientController;
    }


    public void requestWomenCategories(final OnCategoriesResponseListener listener) {
        ApiManager.getService().getWomenCategories(new Callback<CategoriesJSON>() {
            @Override
            public void success(CategoriesJSON categoriesResponse, Response response) {

                Categories categories = JsonToModelTransformHelper.transformCategories(categoriesResponse);
                listener.onWomenCategoriesSuccess(categories);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onResponseError(error);
            }
        });
    }


    public void requestMenCategories(final OnCategoriesResponseListener listener) {
        ApiManager.getService().getMenCategories(new Callback<CategoriesJSON>() {
            @Override
            public void success(CategoriesJSON categoriesResponse, Response response) {

                Categories categories = JsonToModelTransformHelper.transformCategories(categoriesResponse);
                listener.onMenCategoriesSuccess(categories);

            }

            @Override
            public void failure(RetrofitError error) {
                listener.onResponseError(error);
            }
        });
    }

    public void requestProductsInCategory(final String categoryId, final OnProductsResponseListener listener) {
        ApiManager.getService().getProductsInCategory(categoryId, new Callback<ProductsListingJSON>() {
            @Override
            public void success(ProductsListingJSON productsListingResponse, Response response) {
                ProductsListing productsListing = JsonToModelTransformHelper.transformProductsListing(productsListingResponse);
                listener.onProductsListingSuccess(productsListing);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onResponseError(error);
            }
        });
    }

    public void requestProductDetails(final String productId, final OnProductDetailsListener listener) {
        ApiManager.getService().getProductDetails(productId, new Callback<ProductDetailsJSON>() {
            @Override
            public void success(ProductDetailsJSON productsDetailsResponse, Response response) {
                ProductDetails productDetails = JsonToModelTransformHelper.transformProductDetails(productsDetailsResponse);
                listener.onProductsDetailsSuccess(productDetails);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onResponseError(error);
            }
        });
    }
}
