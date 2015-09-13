package com.asos.wojtek.myasosclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asos.wojtek.myasosclient.R;
import com.asos.wojtek.myasosclient.data.model.CategoryDetails;

import java.util.List;

public class DrawerListAdapter extends ArrayAdapter<CategoryDetails> {

    private ViewHolder viewHolder;

    private static class ViewHolder {
        private TextView itemView;
    }

    public DrawerListAdapter(Context context, int textViewResourceId, List<CategoryDetails> items) {
        super(context, textViewResourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.view_drawer_list_item, null);
        }

        CategoryDetails p = getItem(position);

        if (p != null) {
            TextView itemText = (TextView) v.findViewById(R.id.label);

            if (itemText != null) {
                itemText.setText(p.getName());
            }


        }

        return v;
    }
}
