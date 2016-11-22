package com.example.yoga.sqliteexample.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoga.sqliteexample.R;

/**
 * Created by YOGA on 11/7/2016.
 */
public class AddItemFragment extends Fragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_add_item, container, false);

        return myView;
    }
}
