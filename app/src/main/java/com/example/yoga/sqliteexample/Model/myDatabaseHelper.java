package com.example.yoga.sqliteexample.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YOGA on 11/5/2016.
 */


public class myDatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY,";
    private static final String NOT_NULL = " NOT NULL";
    private static final String FOREIGN_KEY = " FOREIGN KEY(";
    private static final String REFERENCES = ") REFERENCES ";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDatabase.db";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /*
    CREATE TABLE person_table (
        person_id   INTEGER PRIMARY KEY,
        name        TEXT,
        email       TEXT
    );
    */

    private static final String CREATE_TABLE_PERSON =
            CREATE_TABLE + Person.PersonEntry.TABLE_NAME + " (" +
                    Person.PersonEntry._ID + PRIMARY_KEY +
                    Person.PersonEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    Person.PersonEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + " );";

    /*
    CREATE TABLE bill_table (
        bill_id     INTEGER PRIMARY KEY,
        place       TEXT,
        date        DATETIME,
        payer       INTEGER NOT NULL,
        FOREIGN KEY(payer) REFERENCES artist(person_id)
    );
     */

    private static final String CREATE_TABLE_BILL =
            CREATE_TABLE + Bill.BillEntry.TABLE_NAME + " (" +
                    Bill.BillEntry._ID + PRIMARY_KEY +
                    Bill.BillEntry.COLUMN_NAME_PLACE + TEXT_TYPE + COMMA_SEP +
                    Bill.BillEntry.COLUMN_NAME_DATE + DATETIME_TYPE + COMMA_SEP +
                    Bill.BillEntry.COLUMN_NAME_PAYER + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    FOREIGN_KEY + Bill.BillEntry.COLUMN_NAME_PAYER + REFERENCES +
                    Person.PersonEntry.TABLE_NAME + "(" + Person.PersonEntry._ID + "));";

    /*
    CREATE TABLE item_table (
        item_id     INTEGER PRIMARY KEY,
        item_name   TEXT,
        price       INTEGER
    );
     */

    private static final String CREATE_TABLE_ITEM =
            CREATE_TABLE + Item.ItemEntry.TABLE_NAME + " (" +
                    Item.ItemEntry._ID + PRIMARY_KEY +
                    Item.ItemEntry.COLUMN_NAME_ITEM_NAME + TEXT_TYPE + COMMA_SEP +
                    Item.ItemEntry.COLUMN_NAME_PRICE + INTEGER_TYPE + " );";

    /*
    CREATE TABLE bill_item_table (
        bill_item_id INTEGER PRIMARY KEY,
        bill_id INTEGER NOT NULL,
        item_id INTEGER NOT NULL,
        FOREIGN KEY (bill_id) REFERENCES bill_table(bill_id),
        FOREIGN KEY (item_id) REFERENCES item_table(item_id)
    )
     */

    private static final String CREATE_TABLE_BILL_ITEM =
            CREATE_TABLE + BillItem.BillItemEntry.TABLE_NAME + " (" +
                    BillItem.BillItemEntry._ID + PRIMARY_KEY +
                    BillItem.BillItemEntry.COLUMN_NAME_BILL_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    BillItem.BillItemEntry.COLUMN_NAME_ITEM_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    FOREIGN_KEY + BillItem.BillItemEntry.COLUMN_NAME_BILL_ID + REFERENCES +
                    Bill.BillEntry.TABLE_NAME + "(" + Bill.BillEntry._ID + ")" + COMMA_SEP +
                    FOREIGN_KEY + BillItem.BillItemEntry.COLUMN_NAME_ITEM_ID + REFERENCES +
                    Item.ItemEntry.TABLE_NAME + "(" + Item.ItemEntry._ID +
                    "));";

    /*
    CREATE TABLE person_bill_table (
        person_bill_id INTEGER PRIMARY KEY,
        person_id INTEGER NOT NULL,
        bill_id INTEGER NOT NULL,
        FOREIGN KEY (person_id) REFERENCES person_table(person_id),
        FOREIGN KEY (bill_id) REFERENCES bill_table(bill_id)
    )
     */

    private static final String CREATE_TABLE_PERSON_BILL =
            CREATE_TABLE + PersonBill.PersonBillEntry.TABLE_NAME + " (" +
                    PersonBill.PersonBillEntry._ID + PRIMARY_KEY +
                    PersonBill.PersonBillEntry.COLUMN_NAME_PERSON_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    PersonBill.PersonBillEntry.COLUMN_NAME_BILL_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    FOREIGN_KEY + PersonBill.PersonBillEntry.COLUMN_NAME_PERSON_ID + REFERENCES +
                    Person.PersonEntry.TABLE_NAME + "(" + Person.PersonEntry._ID + ")" + COMMA_SEP +
                    FOREIGN_KEY + PersonBill.PersonBillEntry.COLUMN_NAME_BILL_ID + REFERENCES +
                    Bill.BillEntry.TABLE_NAME + "(" + Bill.BillEntry._ID + "));";

    /*
    CREATE TABLE person_item_table (
        person_item_id INTEGER PRIMARY KEY,
        person_id INTEGER NOT NULL,
        item_id INTEGER NOT NULL,
        FOREIGN KEY (person_id) REFERENCES person_table(person_id),
        FOREIGN KEY (item_id) REFERENCES item_table(item_id)
    )
     */

    private static final String CREATE_TABLE_PERSON_ITEM =
            CREATE_TABLE + PersonItem.PersonItemEntry.TABLE_NAME + " (" +
                    PersonItem.PersonItemEntry._ID + PRIMARY_KEY +
                    PersonItem.PersonItemEntry.COLUMN_NAME_PERSON_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    PersonItem.PersonItemEntry.COLUMN_NAME_ITEM_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    FOREIGN_KEY + PersonItem.PersonItemEntry.COLUMN_NAME_PERSON_ID + REFERENCES +
                    Person.PersonEntry.TABLE_NAME + "(" + Person.PersonEntry._ID + ")" + COMMA_SEP +
                    FOREIGN_KEY + PersonItem.PersonItemEntry.COLUMN_NAME_ITEM_ID + REFERENCES +
                    Item.ItemEntry.TABLE_NAME + "(" + Item.ItemEntry._ID + "));";


    private static final String DELETE_TABLE_PERSON =
            "DROP TABLE IF EXISTS " + Person.PersonEntry.TABLE_NAME;
    private static final String DELETE_TABLE_BILL =
            "DROP TABLE IF EXISTS " + Bill.BillEntry.TABLE_NAME;
    private static final String DELETE_TABLE_ITEM =
            "DROP TABLE IF EXISTS " + Item.ItemEntry.TABLE_NAME;
    private static final String DELETE_TABLE_BILL_ITEM =
            "DROP TABLE IF EXISTS " + BillItem.BillItemEntry.TABLE_NAME;
    private static final String DELETE_TABLE_PERSON_BILL =
            "DROP TABLE IF EXISTS " + PersonBill.PersonBillEntry.TABLE_NAME;
    private static final String DELETE_TABLE_PERSON_ITEM =
            "DROP TABLE IF EXISTS " + PersonItem.PersonItemEntry.TABLE_NAME;

    public myDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_BILL);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_BILL_ITEM);
        db.execSQL(CREATE_TABLE_PERSON_BILL);
        db.execSQL(CREATE_TABLE_PERSON_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TABLE_PERSON);
        db.execSQL(DELETE_TABLE_BILL);
        db.execSQL(DELETE_TABLE_ITEM);
        db.execSQL(DELETE_TABLE_BILL_ITEM);
        db.execSQL(DELETE_TABLE_PERSON_BILL);
        db.execSQL(DELETE_TABLE_PERSON_ITEM);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long createPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Person.PersonEntry.COLUMN_NAME_NAME, person.getName());
        values.put(Person.PersonEntry.COLUMN_NAME_EMAIL, person.getEmail());

        return db.insert(Person.PersonEntry.TABLE_NAME, null, values);
    }

    /**
     * getting all people
     */
    public List<Person> getAllPeople() {
        List<Person> p = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Person.PersonEntry.TABLE_NAME;

        Log.d(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Person t = new Person(c.getInt((c.getColumnIndex(Person.PersonEntry._ID))),
                        c.getString(c.getColumnIndex(Person.PersonEntry.COLUMN_NAME_NAME)),
                        c.getString(c.getColumnIndex(Person.PersonEntry.COLUMN_NAME_EMAIL)));

                // adding to tags list
                p.add(t);
            } while (c.moveToNext());
        }
        return p;
    }

    /**
     * SELECT * FROM person_table WHERE person_id = long;
     */

    public Person getPerson(long person_id) {
        Person p = new Person();
        String selectQuery = "SELECT  * FROM " + Person.PersonEntry.TABLE_NAME +
                " WHERE " + Person.PersonEntry._ID + " = " + String.valueOf(person_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }

        p.setEmail(c.getString(c.getColumnIndex(Person.PersonEntry.COLUMN_NAME_EMAIL)));
        p.setName(c.getString(c.getColumnIndex(Person.PersonEntry.COLUMN_NAME_NAME)));
        p.setId(c.getInt(c.getColumnIndex(Person.PersonEntry._ID)));

        return p;
    }

    public Bill getBill(long bill_id) {
        Bill bill = new Bill();
        String selectQuery = "SELECT * FROM " + Bill.BillEntry.TABLE_NAME +
                " WHERE " + Bill.BillEntry._ID + " = " + String.valueOf(bill_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }

        bill.setPlace(c.getString(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_PLACE)));
        bill.setPayer(c.getInt(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_PAYER)));

        try {
            bill.setDate(dateFormat.parse(c.getString(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_DATE))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public long createBill(Bill bill) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Bill.BillEntry.COLUMN_NAME_DATE, dateFormat.format(bill.getDate()));
        values.put(Bill.BillEntry.COLUMN_NAME_PLACE, bill.getPlace());
        values.put(Bill.BillEntry.COLUMN_NAME_PAYER, bill.getPayer());

        return db.insert(Bill.BillEntry.TABLE_NAME, null, values);
    }

    public long editBill(Bill bill) {
        Log.d(LOG, "editBill with id = " + String.valueOf(bill.getId()));
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Bill.BillEntry.COLUMN_NAME_DATE, dateFormat.format(bill.getDate()));
        values.put(Bill.BillEntry.COLUMN_NAME_PLACE, bill.getPlace());
        values.put(Bill.BillEntry.COLUMN_NAME_PAYER, bill.getPayer());

        String strFilter = Bill.BillEntry._ID + " = " + String.valueOf(bill.getId());
        return db.update(Bill.BillEntry.TABLE_NAME, values, strFilter, null);
    }

    public List<Bill> getAllBills() {
        List<Bill> billList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Bill.BillEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Bill b = new Bill();

                b.setId(c.getInt(c.getColumnIndex(Bill.BillEntry._ID)));
                b.setPayer(c.getInt(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_PAYER)));
                b.setPlace(c.getString(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_PLACE)));

                Date dt; //replace 4 with the column index
                try {
                    dt = dateFormat.parse(c.getString(c.getColumnIndex(Bill.BillEntry.COLUMN_NAME_DATE)));
                    b.setDate(dt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // adding to tags list
                billList.add(b);
            } while (c.moveToNext());
        }

        return billList;
    }

    public long createItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Item.ItemEntry.COLUMN_NAME_PRICE, item.getPrice());
        values.put(Item.ItemEntry.COLUMN_NAME_ITEM_NAME, item.getItemName());

        return db.insert(Item.ItemEntry.TABLE_NAME, null, values);
    }


    public long editItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Item.ItemEntry.COLUMN_NAME_PRICE, item.getPrice());
        values.put(Item.ItemEntry.COLUMN_NAME_ITEM_NAME, item.getItemName());

        String strFilter = Item.ItemEntry._ID + " = " + String.valueOf(item.getId());
        return db.update(Item.ItemEntry.TABLE_NAME, values, strFilter, null);
    }

    public long createBillItem(BillItem billItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BillItem.BillItemEntry.COLUMN_NAME_BILL_ID, billItem.getBill_id());
        values.put(BillItem.BillItemEntry.COLUMN_NAME_ITEM_ID, billItem.getItem_id());

        return db.insert(BillItem.BillItemEntry.TABLE_NAME, null, values);
    }

    public List<Item> getItemsByBill(long bill_id) {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + BillItem.BillItemEntry.TABLE_NAME + " WHERE " + BillItem.BillItemEntry.COLUMN_NAME_BILL_ID + " = " + String.valueOf(bill_id);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Log.d(LOG, "Query billItem table with id: " + String.valueOf(bill_id) + ", row count = " + String.valueOf(c.getCount()));

        // looping through all rows in billItem
        if (c.moveToFirst()) {
            do {
                String item_id = String.valueOf(c.getLong(c.getColumnIndex(BillItem.BillItemEntry.COLUMN_NAME_ITEM_ID)));
                Log.d(LOG, "Finding Item with id = " + item_id);
                String itemQuery = "SELECT * FROM " + Item.ItemEntry.TABLE_NAME + " WHERE " + Item.ItemEntry._ID + " = " + item_id;
                Cursor itemCursor = db.rawQuery(itemQuery, null);

                if (itemCursor.moveToFirst()) {
                    Item item = new Item();
                    item.setPrice(itemCursor.getInt(itemCursor.getColumnIndex(Item.ItemEntry.COLUMN_NAME_PRICE)));
                    item.setItemName(itemCursor.getString(itemCursor.getColumnIndex(Item.ItemEntry.COLUMN_NAME_ITEM_NAME)));
                    itemList.add(item);
                }
            } while (c.moveToNext());
        }

        Log.d(LOG, "itemList size = " + String.valueOf(itemList.size()));
        return itemList;
    }
}
