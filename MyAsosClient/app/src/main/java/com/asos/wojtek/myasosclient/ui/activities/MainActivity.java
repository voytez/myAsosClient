package com.asos.wojtek.myasosclient.ui.activities;

import android.content.Context;
import android.os.Bundle;

import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.data.database.enums.SavedItemType;
import com.asos.wojtek.myasosclient.data.model.Categories;
import com.asos.wojtek.myasosclient.ui.fragments.ProductDetailsFragment;
import com.asos.wojtek.myasosclient.network.listeners.OnCategoriesResponseListener;
import com.asos.wojtek.myasosclient.utils.SharedPreferencesManager;

import butterknife.OnClick;
import retrofit.RetrofitError;

/**
 * activity handling client api responses and callback from other fragments
 */
public class MainActivity extends BaseActivity implements OnCategoriesResponseListener, ProductDetailsFragment.OnAddToBagListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create rest api client
        clearSavedCategories();

        apiClient.requestWomenCategories(this);
        apiClient.requestMenCategories(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //instead of calling save selected category tab every time user switches, save it when app goes to background or get killed
        SharedPreferencesManager.getInstance().setKeyValue(SharedPreferencesManager.SELECTED_CATEGORY_KEY, selectedCategory.name());

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBagSize();
    }


    @OnClick(R.id.try_again_button)
    @SuppressWarnings("unused")
    /**
     * handling he default "try again" button displayed on network connection error
     */
    void handleRetryButtonClick() {
        apiClient.requestWomenCategories(MainActivity.this);
        apiClient.requestMenCategories(MainActivity.this);
        hideConnectionError();
    }

    private void updateBagSize() {
        int bagSize = savedItemsDatabaseAdapter.getSavedItemsSize(SavedItemType.BAG.name());
        textBagSize.setText(String.valueOf(bagSize));
    }

    /**
     * callback from ProductDetailsFragment after successful adding of saved item to database
     */
    @Override
    public void onAddToBagSuccess() {
        updateBagSize();
        Toast.makeText(this, R.string.item_add_to_bag, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWomenCategoriesSuccess(Categories categoriesListing) {
        handleSuccessfulResponse(categoriesListing);
    }

    @Override
    public void onMenCategoriesSuccess(Categories categoriesListing) {
        handleSuccessfulResponse(categoriesListing);
    }

    @Override
    public void onResponseError(RetrofitError error) {
        showConnectionError();
    }

    private void handleSuccessfulResponse(Categories categoriesListing) {
        saveCategory(categoriesListing);
        if (selectedCategory.name().equals(categoriesListing.getDescription())) {
            updateCategoryTab(categoriesListing.getDescription());
            showNavigationDrawerList();
            hideConnectionError();
        }
    }

}
