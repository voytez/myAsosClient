package com.asos.wojtek.myasosclient.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.ui.adapter.ProductsGridAdapter;
import com.asos.wojtek.myasosclient.data.model.ProductItem;
import com.asos.wojtek.myasosclient.data.model.ProductsListing;
import com.asos.wojtek.myasosclient.network.listeners.OnProductsResponseListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.RetrofitError;


/**
 * fragment displaying products by category in a grid layout
 */
public class ProductsListingFragment extends BaseFragment implements OnProductsResponseListener, AdapterView.OnItemClickListener {


    public static final String ARG_SELECTED_CATEGORY = "selected_category";

    private static final String FRAGMENT_TAG = "product_listing_fragment_tag";

    //set the interval for handling double-clicks, to prevent adding fragment twice
    private static final int MISCLICK_INTERVAL = 1000;


    @Bind(R.id.products_title)
    TextView productsTitle;

    @Bind(R.id.item_count)
    TextView itemsCountText;

    @Bind(R.id.products_grid)
    GridView productsGrid;

    private final List<ProductItem> productsList = new ArrayList<>();
    private ProductsGridAdapter productsGridAdapter;
    private long lastClickTime;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productsGridAdapter = new ProductsGridAdapter(getActivity(), R.layout.view_product_grid_item, productsList);
        productsGrid.setAdapter(productsGridAdapter);
        productsGrid.setOnItemClickListener(this);
    }

    public static ProductsListingFragment newInstance(String categoryId) {
        ProductsListingFragment fragment = new ProductsListingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SELECTED_CATEGORY, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_products_browser;
    }

    @Override
    protected void makeNetworkRequest() {
        apiClientController.requestProductsInCategory(getArguments().getString(ARG_SELECTED_CATEGORY), this);
    }

    @Override
    public void onProductsListingSuccess(ProductsListing productsListing) {
        updateData(productsListing);
    }

    /**
     * update the ui with new data set and hide loading animation
     * @param productsListing response model object
     */
    private void updateData(ProductsListing productsListing) {
        productsList.clear();
        productsList.addAll(productsListing.getProductsListing());
        productsTitle.setText(productsListing.getDescription());
        itemsCountText.setText(String.valueOf(productsListing.getItemCount()));
        setLoadingAnimationVisibility(false);
    }

    @Override
    public void onResponseError(RetrofitError error) {
        showConnectionError();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String productId = productsGridAdapter.getItem(position).getProductId();
        Fragment fragment = ProductDetailsFragment.newInstance(productId);

        // mis-clicking prevention, using threshold of 1000 ms
        // also could be used to handle double click for adding to wishlist but preferably GestureDetector should be used
        if (SystemClock.elapsedRealtime() - lastClickTime < MISCLICK_INTERVAL) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();


        //ADD not REPLACE! back button on replaced fragment would recreate previous one causing unnecessary server request
        //code above will prevent from adding fragment more than once on double click
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, FRAGMENT_TAG).addToBackStack(null).commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshGridView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        refreshGridView();
    }

    /**
     * refresh products browser gird view based on orientation
     */
    private void refreshGridView() {
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            productsGrid.setNumColumns(getResources().getInteger(R.integer.num_columns));
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            productsGrid.setNumColumns(getResources().getInteger(R.integer.num_columns_land));
        }
    }

}
