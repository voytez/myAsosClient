package com.asos.wojtek.myasosclient.network;

import retrofit.RestAdapter;

public class ApiManager {

    private static String API_URL = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.NONE)
            .build();

    private static final AsosApiInterface asosApiClient = REST_ADAPTER.create(AsosApiInterface.class);
    public static AsosApiInterface getService() { return  asosApiClient;}

}
