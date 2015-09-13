package com.asos.wojtek.myasosclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class manages handling shared preferences
 */
public class SharedPreferencesManager {
    private static final String PREF_NAME = "com.asos.wojtek.myasosclient.PREF_NAME";
    public static final String SELECTED_CATEGORY_KEY = "selectedCateogry";


    private static SharedPreferencesManager sInstance;
    private final SharedPreferences sharedPref;

    private SharedPreferencesManager(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesManager(context);
        }
    }

    public static synchronized SharedPreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SharedPreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public boolean contains(String key){
     return sharedPref.contains(key);
    }

    public void setKeyValue(String key, String value) {
        sharedPref.edit()
                .putString(key, value)
                .commit();
    }

    public String getValueForKey(String key) {
        return sharedPref.getString(key, null);
    }

    public void removeKey(String key) {
        sharedPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return sharedPref.edit()
                .clear()
                .commit();
    }
}
