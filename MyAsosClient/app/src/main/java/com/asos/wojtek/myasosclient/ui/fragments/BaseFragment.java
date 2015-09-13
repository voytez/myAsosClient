package com.asos.wojtek.myasosclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.network.ApiClientController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * base fragment class for all fragments, handling network, connection error msg etc
 */
public abstract class BaseFragment extends Fragment {

    @Bind(R.id.progress_container)
    protected View progressContainer;

    @Bind(R.id.error_container)
    View connectionError;

    protected ApiClientController apiClientController;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientController = ApiClientController.getInstance();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeNetworkRequest();
        progressContainer.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.try_again_button)
    @SuppressWarnings("unused")
    void handleRetryButtonClick(){
        progressContainer.setVisibility(View.VISIBLE);
        connectionError.setVisibility(View.GONE);
        makeNetworkRequest();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected abstract void makeNetworkRequest();

    protected void setLoadingAnimationVisibility(boolean isVisible){
        progressContainer.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    protected void showConnectionError() {
        if(progressContainer != null) {
            progressContainer.setVisibility(View.GONE);
        }
        if(connectionError != null) {
            connectionError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

