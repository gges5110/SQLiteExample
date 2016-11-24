package com.example.yoga.sqliteexample.Model;

import android.provider.BaseColumns;

import java.text.SimpleDateFormat;

/**
 * Created by YOGA on 11/6/2016.
 */

public final class Item {
    private int price, id;
    private String itemName;

    public Item(int id, int price, String itemName) {
        this.id = id;
        this.price = price;
        this.itemName = itemName;
    }

    public Item() {}


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "item_table";
        public static final String _ID = "item_id";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_ITEM_NAME = "item_name";
    }
}
