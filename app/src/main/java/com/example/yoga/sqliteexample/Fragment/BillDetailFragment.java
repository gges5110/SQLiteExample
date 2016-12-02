package com.example.yoga.sqliteexample.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.Bill;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by YOGA on 11/19/2016.
 */

public class BillDetailFragment extends Fragment {
    public static final String TAG = "BillDetailFragment";
    private TextView bill_detail_textView_place, bill_detail_textView_date, bill_detail_textView_payer;
    private Button bill_detail_add_button, bill_detail_edit_button;
    private int bill_id;
    private Bill bill;
    private Person payer;

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public interface BillDetailFragmentInterface {
        void setEditBillFragment(int bill_id);
        Person getPerson(long person_id);
        Bill getBill(long bill_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateView");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        View myView = inflater.inflate(R.layout.fragment_bill_detail, container, false);
        bill_detail_textView_place = (TextView) myView.findViewById(R.id.bill_detail_textView_place);
        bill_detail_textView_date = (TextView) myView.findViewById(R.id.bill_detail_textView_date);
        bill_detail_textView_payer = (TextView) myView.findViewById(R.id.bill_detail_textView_payer);
        bill_detail_add_button = (Button) myView.findViewById(R.id.bill_detail_add_button);
        bill_detail_edit_button = (Button) myView.findViewById(R.id.bill_detail_edit_button);

        Activity activity = getActivity();
        try {
            BillDetailFragmentInterface mListener = (BillDetailFragmentInterface) activity;
            Log.d(TAG, "Bill Id = " + String.valueOf(bill_id));
            bill = mListener.getBill(bill_id);
            payer = mListener.getPerson(bill.getPayer());
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }


        bill_detail_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                try {
                    BillDetailFragmentInterface mListener = (BillDetailFragmentInterface) activity;
                    mListener.setEditBillFragment(bill_id);
                } catch (ClassCastException e) {
                    throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
                }
            }
        });

        if (bill != null) {
            bill_detail_textView_place.setText(bill.getPlace());
            bill_detail_textView_date.setText(dateFormat.format(bill.getDate()));
        }

        if (payer != null) {
            bill_detail_textView_payer.setText(payer.getName());
        }

        printFragmentStackName();
        return myView;
    }



    private void printFragmentStackName() {
        FragmentManager fm = getFragmentManager();
        int count = getFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "back stack count = " + String.valueOf(count));
        for(int entry = 0; entry < fm.getBackStackEntryCount(); entry++){
            Log.i(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getName());
        }
    }

}
