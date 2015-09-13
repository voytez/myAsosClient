package com.asos.wojtek.myasosclient.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.data.model.ProductItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsGridAdapter extends ArrayAdapter<ProductItem> {

    private LayoutInflater inflater;

    public ProductsGridAdapter(Context context, int resource, List<ProductItem> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.view_product_grid_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        populateData(viewHolder, position);

        return view;
    }

    private void populateData(final ViewHolder viewHolder, int position) {
        ProductItem product = getItem(position);
        viewHolder.gridText.setText(product.getProductPrice());
        if (product.getProductImageUrl() != null && !product.getProductImageUrl().isEmpty()) {
            Picasso.with(getContext()).load(product.getProductImageUrl()).error(R.drawable.wishlist).into(viewHolder.gridImage,  new Callback() {
                @Override
                public void onSuccess() {
                    viewHolder.progressContainer.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'view_grid_item.xml'
     * for easy to all layout elements.
     */
    static class ViewHolder {
        @Bind(R.id.grid_image)
        ImageView gridImage;
        @Bind(R.id.grid_text_name)
        TextView gridText;
        @Bind(R.id.progress_container)
        View progressContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}