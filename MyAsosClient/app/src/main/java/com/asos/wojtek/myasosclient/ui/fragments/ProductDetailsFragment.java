package com.asos.wojtek.myasosclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.data.database.enums.SavedItemType;
import com.asos.wojtek.myasosclient.ui.adapter.FragmentPagerAdapter;
import com.asos.wojtek.myasosclient.data.database.SavedItemsDatabaseAdapter;
import com.asos.wojtek.myasosclient.data.model.ProductDetails;
import com.asos.wojtek.myasosclient.network.listeners.OnProductDetailsListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.RetrofitError;

/**
 * fragment displaying and handling product details screen
 */
public class ProductDetailsFragment extends BaseFragment implements OnProductDetailsListener {


    private static final String ARG_PRODUCT_ID = "arg_product_id";

    @Bind(R.id.product_image_pager)
    ViewPager imageViewPager;

    @Bind(R.id.product_details_brand_name)
    TextView textBrandName;

    @Bind(R.id.product_details_title)
    TextView textTitle;

    @Bind(R.id.product_details_price)
    TextView textPrice;

    @Bind(R.id.product_details_description)
    TextView textDescription;

    @Bind(R.id.product_details_additional_info)
    TextView textAdditionalInfo;

    @Bind(R.id.product_details_add_to_basket_button)
    Button buttonAddToBasket;

    private SavedItemsDatabaseAdapter savedItemsDatabaseAdapter;

    private String productId;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();


    public static ProductDetailsFragment newInstance(String productId) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedItemsDatabaseAdapter = new SavedItemsDatabaseAdapter(getActivity());
        fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(), fragments);
        imageViewPager.setAdapter(fragmentPagerAdapter);

        if (getArguments() != null) {
            productId = getArguments().getString(ARG_PRODUCT_ID);
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_product_details;
    }

    @Override
    protected void makeNetworkRequest() {
        apiClientController.requestProductDetails(productId, this);
    }

    @Override
    public void onProductsDetailsSuccess(ProductDetails productsResponse) {
        updateData(productsResponse);
    }

    @Override
    public void onResponseError(RetrofitError error) {
        showConnectionError();
    }

    /**
     * update the ui with new data set and hide loading animation
     * @param productDetails response model object
     */
    private void updateData(ProductDetails productDetails) {
        fragments = buildFragments(productDetails.getImageUrls());
        fragmentPagerAdapter.notifyDataSetChanged();
        textBrandName.setText(productDetails.getBrand());
        textTitle.setText(productDetails.getName());
        textPrice.setText(productDetails.getPrice());
        textDescription.setText(productDetails.getDescription());
        textAdditionalInfo.setText(productDetails.getAdditionalInfo());
        setLoadingAnimationVisibility(false);
        buttonAddToBasket.setVisibility(View.VISIBLE);
    }

    private List<Fragment> buildFragments(List<String> imageUrls) {
        for (String imageUrl : imageUrls) {
            fragments.add(ImageViewFragment.newInstance(imageUrl));
        }
        return fragments;
    }

    @OnClick(R.id.product_details_add_to_basket_button)
    @SuppressWarnings("unused")
    void addToBag() {
        //check if the db insertion returned proper row number, -1 if error
        if (savedItemsDatabaseAdapter.addToSavedItem(productId, SavedItemType.BAG.name()) > -1) {
            try {
                ((OnAddToBagListener) getActivity()).onAddToBagSuccess();
            } catch (ClassCastException cce) {
                throw new RuntimeException("Does your activity class implement OnAddToBagListener?");
            }
        }
    }

    public interface OnAddToBagListener {
        public void onAddToBagSuccess();
    }


}
