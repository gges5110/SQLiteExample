package com.example.yoga.sqliteexample.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.Item;
import com.example.yoga.sqliteexample.R;

import java.util.List;

/**
 * Created by YOGA on 3/27/2017.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<Item> itemList;

    public ItemRecyclerViewAdapter(Activity activity, List<Item> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_adapter, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new ViewHolder((LinearLayout) v, activity, itemList);
    }

    @Override
    public void onBindViewHolder(ItemRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView_itemListAdapter_itemName.setText(itemList.get(position).getItemName());
        holder.textView_itemListAdapter_price.setText(String.valueOf(itemList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_itemListAdapter_itemName, textView_itemListAdapter_price;
        private Activity activity;
        private List<Item> itemList;

        public ViewHolder(LinearLayout itemView, Activity activity, List<Item> itemList) {
            super(itemView);
            this.activity = activity;
            this.itemList = itemList;

            textView_itemListAdapter_itemName = (TextView) itemView.findViewById(R.id.textView_itemListAdapter_itemName);
            textView_itemListAdapter_price = (TextView) itemView.findViewById(R.id.textView_itemListAdapter_price);
        }
    }
}
