package com.example.yoga.sqliteexample.Model;

import java.util.Date;

/**
 * Created by YOGA on 11/5/2016.
 */

public final class Bill {
    private int id, payer;
    private String place;
    private Date date;

    public Bill(int id, String place, Date date, int payer) {
        this.id = id;
        this.place = place;
        this.date = date;
        this.payer = payer;
    }

    public Bill() {
    }

    public int getPayer() {
        return payer;
    }

    public void setPayer(int payer) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
