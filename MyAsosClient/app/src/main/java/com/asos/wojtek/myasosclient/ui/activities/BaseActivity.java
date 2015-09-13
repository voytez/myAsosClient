package com.asos.wojtek.myasosclient.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.data.database.enums.CategoryName;
import com.asos.wojtek.myasosclient.network.ApiClientController;
import com.asos.wojtek.myasosclient.ui.adapter.DrawerListAdapter;
import com.asos.wojtek.myasosclient.data.database.SavedItemsDatabaseAdapter;
import com.asos.wojtek.myasosclient.data.database.CategoriesDatabaseAdapter;
import com.asos.wojtek.myasosclient.data.model.Categories;
import com.asos.wojtek.myasosclient.data.model.CategoryDetails;
import com.asos.wojtek.myasosclient.ui.fragments.ProductsListingFragment;
import com.asos.wojtek.myasosclient.utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * activity that contains the navigation drawer
 * and anything related to handling menu options and toolbar actions.
 */
public class BaseActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.drawer_linear)
    LinearLayout drawerLinear;

    @Bind(R.id.left_drawer)
    ListView drawerList;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabLayout)
    TabLayout categoryTabs;

    @Bind(R.id.logo)
    ImageView logoIcon;

    @Bind(R.id.loadingCircle)
    ProgressBar navigationDrawerProgress;

    @Bind(R.id.error_container)
    View connectionError;

    @Bind(R.id.text_bag_size)
    TextView textBagSize;

    private static final String FRAGMENT_TAG = "main_fragment_tag";

    //used to load products list fragment pretending to be landing page
    private static final String DEFAULT_CATEGORY_ID = "0";

    private ActionBarDrawerToggle drawerToggle;

    public DrawerListAdapter listViewAdapter;

    protected final List<CategoryDetails> currentCategories = new ArrayList<>();

    protected CategoryName selectedCategory = CategoryName.WOMEN;

    protected CategoriesDatabaseAdapter categoriesDatabaseAdapter;

    protected SavedItemsDatabaseAdapter savedItemsDatabaseAdapter;

    protected ApiClientController apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //create database adapter
        categoriesDatabaseAdapter = new CategoriesDatabaseAdapter(this);
        savedItemsDatabaseAdapter = new SavedItemsDatabaseAdapter(this);

        apiClient = ApiClientController.getInstance();

        initializeNavigationDrawer();

        SharedPreferencesManager.initializeInstance(this);
        loadSelectedCategory();

        setActionBarIconsListener();

        buildTabs();

        //add fragment with default category id which is pretending to be landing page
        loadProductsListingFragment(0);

    }

    private void loadSelectedCategory() {
        if (SharedPreferencesManager.getInstance().contains(SharedPreferencesManager.SELECTED_CATEGORY_KEY)) {
            selectedCategory = CategoryName.valueOf(SharedPreferencesManager.getInstance().getValueForKey(SharedPreferencesManager.SELECTED_CATEGORY_KEY));
        }
    }

    private void initializeNavigationDrawer() {

        listViewAdapter = new DrawerListAdapter(this, R.layout.view_drawer_list_item, currentCategories);

        // set up the drawer's list view with items and click listener
        drawerList.setAdapter(listViewAdapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        setSupportActionBar(toolbar);

        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDelegate().getSupportActionBar().setHomeButtonEnabled(true);
        getDelegate().getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setActionBarIconsListener() {

        logoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerLinear);
            }
        });
    }

    private void buildTabs() {
        //add tabs from CategoryNames enum and pre select with value stored in shared preferences
        for (CategoryName tabCategory : CategoryName.values()) {
            categoryTabs.addTab(buildTab(tabCategory.name()), tabCategory.equals(selectedCategory));
        }
        categoryTabs.setOnTabSelectedListener(this);

    }

    private TabLayout.Tab buildTab(String categoryName) {
        return categoryTabs.newTab()
                .setText(categoryName)
                .setTag(categoryName)
                .setContentDescription(categoryName);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getText() != null && !tab.getText().toString().isEmpty()) {
            selectedCategory = CategoryName.valueOf(tab.getText().toString());
            switch (selectedCategory) {
                case WOMEN:
                    updateCategoryTab(CategoryName.WOMEN.name());
                    break;
                case MEN:
                    updateCategoryTab(CategoryName.MEN.name());
                    break;
            }
        }
    }

    protected void updateCategoryTab(String category) {
        currentCategories.clear();
        if (categoriesDatabaseAdapter.getCategory(category) != null) {
            currentCategories.addAll(categoriesDatabaseAdapter.getCategory(category).getListing());
            listViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        loadProductsListingFragment(position);
        drawerLayout.closeDrawer(drawerLinear);
    }

    protected void loadProductsListingFragment(int position) {

        // update the main content by replacing fragments
        String categoryId;

        if (currentCategories != null && currentCategories.size() > 0) {
            categoryId = currentCategories.get(position).getCategoryId();
        } else {
            categoryId = DEFAULT_CATEGORY_ID;
        }
        Fragment fragment = ProductsListingFragment.newInstance(categoryId);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, FRAGMENT_TAG).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }


    public void saveCategory(Categories categoriesListing) {
        categoriesDatabaseAdapter.insert(categoriesListing.getDescription(), categoriesListing);
    }

    public void clearSavedCategories() {
        categoriesDatabaseAdapter.deleteAllCategories();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerLinear)) {
            drawerLayout.closeDrawer(drawerLinear);
        } else {
            super.onBackPressed();  // optional depending on your needs
        }
    }

    protected void showConnectionError() {
        connectionError.setVisibility(View.VISIBLE);
    }

    protected void hideConnectionError() {
        connectionError.setVisibility(View.GONE);
    }

    protected void showNavigationDrawerList() {
        navigationDrawerProgress.setVisibility(View.GONE);
        drawerList.setVisibility(View.VISIBLE);
    }
}

