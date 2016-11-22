package com.example.yoga.sqliteexample.Fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoga.sqliteexample.MainPage;
import com.example.yoga.sqliteexample.R;

/**
 * Created by YOGA on 11/19/2016.
 */

public class BillDetailFragment extends Fragment {
    public static final String TAG = "BillDetailFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void printFragmentStackName() {
        FragmentManager fm = getFragmentManager();
        Log.i(TAG, "Finding fragments.");
        int count = getFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "back stack count = " + String.valueOf(count));
        for(int entry = 0; entry < fm.getBackStackEntryCount(); entry++){
            Log.i(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_bill_detail, container, false);
        printFragmentStackName();
        return myView;
    }

}
