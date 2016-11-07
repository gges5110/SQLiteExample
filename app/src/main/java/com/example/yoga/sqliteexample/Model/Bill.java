package com.example.yoga.sqliteexample.Model;

import android.provider.BaseColumns;

/**
 * Created by YOGA on 11/5/2016.
 */

public final class Bill {
    private int id;
    private String place, date, payer;

    public Bill(int id, String place, String date, String payer) {
        this.id = id;
        this.place = place;
        this.date = date;
        this.payer = payer;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public static class BillEntry {
        public static final String TABLE_NAME = "bill_table";
        public static final String _ID = "bill_id";
        public static final String COLUMN_NAME_PLACE = "place";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_PAYER = "payer";
    }
}
