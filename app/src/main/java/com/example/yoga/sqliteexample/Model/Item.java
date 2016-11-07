package com.example.yoga.sqliteexample.Model;

import android.provider.BaseColumns;

import java.text.SimpleDateFormat;

/**
 * Created by YOGA on 11/6/2016.
 */

public final class Item {
    public Item(int id, int price) {
        this.id = id;
        this.price = price;
    }

    private int price, id;

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
    }
}
