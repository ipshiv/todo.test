package com.example.android.activityscenetransitionbasic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.activityscenetransitionbasic.R;
import com.example.android.activityscenetransitionbasic.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private static final String TAG = "GridAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Item> mItems = new ArrayList();

    public GridAdapter(Context mContext, List<Item> mItems) {
        this.mContext = mContext;
        this.mInflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        final Item item = getItem(position);

        // Load the thumbnail image
        ImageView image = (ImageView) view.findViewById(R.id.imageview_item);
        Picasso.with(image.getContext()).load(item.getThumbnailUrl()).into(image);

        // Set the TextView's contents
        TextView name = (TextView) view.findViewById(R.id.textview_name);
        name.setText(item.getName());

        return view;
    }
}
