package com.example.yoga.sqliteexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.yoga.sqliteexample.Adapter.BillRecyclerViewAdapter;
import com.example.yoga.sqliteexample.Fragment.*;
import com.example.yoga.sqliteexample.Model.*;

import java.util.List;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AddPersonFragment.AddPersonInterface,
        AddBillFragment.AddBillInterface,
        AddItemFragment.AddItemInterface,
        ListBillFragment.ListBillInterface,
        ListPersonFragment.ListPersonInterface,
        BillRecyclerViewAdapter.BillRecyclerInterface,
        BillDetailFragment.BillDetailFragmentInterface {

    private static final String TAG = "MainPage";
    // Database Helper
    private myDatabaseHelper db;
    private ActionBarDrawerToggle drawerToggle;
    private android.support.v7.app.ActionBar actionBar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(drawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_list_bill);

        db = new myDatabaseHelper(getApplicationContext());
        showHomeIcon();
    }

    private void showHomeIcon() {
        // This is a very weird bug, I need to turn home icon into back arrow
        // and turn in back in order for the hamburger icon to show up
        displayHomeIcon(false);
        displayHomeIcon(true);
    }

    private void printFragmentStackName() {
        FragmentManager fm = getFragmentManager();
        int count = getFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "back stack count = " + String.valueOf(count));

        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            Log.i("TAG", "Found fragment" + String.valueOf(entry) + ": " + fm.getBackStackEntryAt(entry).getName());
        }
    }

    private boolean displayHomeIcon = true;

    private boolean getDisplayHomeIcon() {
        return displayHomeIcon;
    }

    private void displayHomeIcon(boolean shouldDisplayHomeIcon) {
        displayHomeIcon = shouldDisplayHomeIcon;
        drawerToggle.setDrawerIndicatorEnabled(shouldDisplayHomeIcon);
        if (!shouldDisplayHomeIcon) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected.");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            if (!getDisplayHomeIcon()) {
                popFragmentStack();
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed().");
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!getDisplayHomeIcon()) {
            popFragmentStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void popFragmentStack() {
        getFragmentManager().popBackStack();
        String tag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName();
        actionBar.setTitle(tag);
        getFragmentManager().executePendingTransactions();
        displayHomeIcon(getFragmentManager().getBackStackEntryCount() == 0);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayView(id);
        Log.d(TAG, "onNavigationItemSelected id = " + String.valueOf(id));
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
//            case R.id.nav_add_person:
//                fragment = new AddPersonFragment();
//                title  = "Add Person";
//                break;
//            case R.id.nav_add_bill:
//                fragment = new AddBillFragment();
//                title = "Add Bill";
//                break;
//            case R.id.nav_add_item:
//                // fragment = new AddItemFragment();
//                // title = "Add item";
//                break;
            case R.id.nav_list_person:
                fragment = new ListPersonFragment();
                title = "List Person";
                break;
            case R.id.nav_list_bill:
                fragment = new ListBillFragment();
                title = "List Bill";
                break;
        }

        if (fragment != null) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            getFragmentManager().executePendingTransactions();
        }

        // set the toolbar title
        actionBar.setTitle(title);
        displayHomeIcon(true);

        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public long createPerson(Person p) {
        return db.createPerson(p);
    }

    @Override
    public List<Person> getAllPeople() {
        return db.getAllPeople();
    }

    @Override
    public Person getPerson(long person_id) {
        return db.getPerson(person_id);
    }

    @Override
    public Bill getBill(long bill_id) {
        return db.getBill(bill_id);
    }

    @Override
    public List<Item> getItemsByBill(long bill_id) {
        return db.getItemsByBill(bill_id);
    }

    @Override
    public long createBill(Bill bill) {
        return db.createBill(bill);
    }

    @Override
    public long editBill(Bill bill) {
        return db.editBill(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return db.getAllBills();
    }

    @Override
    public long createItem(Item item) {
        return db.createItem(item);
    }

    @Override
    public long editItem(Item item) {
        return db.editItem(item);
    }

    @Override
    public long createBillItem(BillItem billItem) {
        return db.createBillItem(billItem);
    }

    @Override
    public void setBillDetailFragment(int bill_id) {
        BillDetailFragment billDetailFragment = new BillDetailFragment();
        billDetailFragment.setBill_id(bill_id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, billDetailFragment);
        ft.addToBackStack("Bill Details");
        ft.commit();

        actionBar.setTitle("Bill Details");
        displayHomeIcon(false);
        printFragmentStackName();
    }

    @Override
    public void setEditBillFragment(int bill_id) {
        AddBillFragment addBillFragment = new AddBillFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.content_frame, addBillFragment);
        ft.addToBackStack("Bill Details");
        ft.commit();
        getFragmentManager().executePendingTransactions();

        actionBar.setTitle("Edit Bill");
        displayHomeIcon(false);
        addBillFragment.setBill(bill_id);
        printFragmentStackName();
    }

    private void setAddBillFragment() {
        AddBillFragment addBillFragment = new AddBillFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.content_frame, addBillFragment);
        ft.addToBackStack("List Bill");
        ft.commit();

        actionBar.setTitle("Add Bill");
        displayHomeIcon(false);
    }

    private void setAddPersonFragment() {
        AddPersonFragment addPersonFragment = new AddPersonFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, addPersonFragment);
        ft.addToBackStack("List Person");
        ft.commit();

        actionBar.setTitle("Add Person");
        displayHomeIcon(false);
    }

    private void setAddItemFragment(int bill_id) {
        AddItemFragment addItemFragment = new AddItemFragment();
        addItemFragment.setBillID(bill_id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, addItemFragment);
        ft.addToBackStack("Bill Details");
        ft.commit();

        actionBar.setTitle("Add Item");
        displayHomeIcon(false);
    }

    public void floatingActionButtonOnClick(View view) {
        Log.d(TAG, "floatingActionButtonOnClick");
        Log.d(TAG, "getDisplayHomeIcon() = " + String.valueOf(getDisplayHomeIcon()));
        printFragmentStackName();
        // Check which fragment it is in.
        Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);
        if (f instanceof ListBillFragment) {
            Log.d(TAG, "ListBillFragment");
            // Replace current fragment with AddBillFragment
            setAddBillFragment();

        } else if (f instanceof ListPersonFragment) {
            Log.d(TAG, "ListPersonFragment");
            // Replace current fragment with AddPersonFragment
            setAddPersonFragment();
        } else if (f instanceof BillDetailFragment) {
            Log.d(TAG, "BillDetailFragment");
            setAddItemFragment(BillDetailFragment.bill_id);
        }

    }


}
