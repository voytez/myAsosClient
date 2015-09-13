package com.asos.wojtek.myasosclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.asos.wojtek.myasosclient.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;


/**
 * fragment used by products browser to display single grid view image fragment
 * has to extend base fragment to handle loading animation
 */
public class ImageViewFragment extends BaseFragment {

    private static final String ARG_IMAGE_URL = "arg_image_url";

    @Bind(R.id.image_pager_item)
    ImageView imageView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_image_pager_item;
    }

    @Override
    protected void makeNetworkRequest() {

    }

    public static ImageViewFragment newInstance(@NonNull final String imageUrl) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.with(getActivity()).load(getArguments().getString(ARG_IMAGE_URL)).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                setLoadingAnimationVisibility(false);
            }

            @Override
            public void onError() {

            }
        });

    }


}
