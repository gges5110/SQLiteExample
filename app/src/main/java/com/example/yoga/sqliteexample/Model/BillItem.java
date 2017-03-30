package com.example.yoga.sqliteexample.Model;

/**
 * Created by YOGA on 11/6/2016.
 */

public final class BillItem {
    private int id, item_id, bill_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public BillItem() {
    }

    public static class BillItemEntry {
        public static final String TABLE_NAME = "bill_item_table";
        public static final String _ID = "bill_item_id";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_BILL_ID = "bill_id";
    }
}
