package com.example.yoga.sqliteexample.Model;

import android.provider.BaseColumns;

/**
 * Created by YOGA on 11/5/2016.
 */

public final class Person {
    private int id;
    private String name, email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {

    }

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static class PersonEntry {
        public static final String TABLE_NAME = "person_table";
        public static final String _ID = "person_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
