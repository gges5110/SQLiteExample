package com.example.yoga.sqliteexample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;


import android.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.Bill;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.Model.myDatabaseHelper;
import com.example.yoga.sqliteexample.fragments.AddBillFragment;
import com.example.yoga.sqliteexample.fragments.AddItemFragment;
import com.example.yoga.sqliteexample.fragments.AddPersonFragment;
import com.example.yoga.sqliteexample.fragments.ListBillFragment;
import com.example.yoga.sqliteexample.fragments.ListPersonFragment;

import java.util.List;

public class MainPage extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener,
                    AddPersonFragment.AddPersonInterface,
                    ListPersonFragment.ListPersonInterface,
                    AddBillFragment.AddBillInterface,
                    ListBillFragment.ListBillInterface {

    // Database Helper
    myDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_add_person);

        db = new myDatabaseHelper(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayView(id);
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_add_person:
                fragment = new AddPersonFragment();
                title  = "Add Person";

                break;
            case R.id.nav_add_bill:
                fragment = new AddBillFragment();
                title = "Add Bill";
                break;
            case R.id.nav_add_item:
                fragment = new AddItemFragment();
                title = "Add item";
                break;
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
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public long createBill(Bill bill) {
        return db.createBill(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return db.getAllBills();
    }
}
