package com.example.yoga.sqliteexample.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yoga.sqliteexample.Model.Bill;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOGA on 11/7/2016.
 */
public class ListBillFragment extends Fragment {
    private ListView listView_list_bill;

    public interface ListBillInterface {
        List<Bill> getAllBills();
        Person getPerson(long person_id);
    }


    ListBillInterface mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                mListener = (ListBillInterface) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
            }

        } else {
            throw new ClassCastException(context.toString() + " not an instance of activity.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_list_bill, container, false);
        listView_list_bill = (ListView) myView.findViewById(R.id.listView_list_bill);
        List<Bill> billList = mListener.getAllBills();
        List<String> billStringList = new ArrayList<>();

        for (int i = 0; i < billList.size(); ++i) {
            Bill b = billList.get(i);
            Person p = mListener.getPerson(b.getPayer());
            billStringList.add(b.getPlace() + ", payer = " + p.getName() + ", at " + b.getDate().toString());
        }

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(myView.getContext(), R.layout.listview_layout, billStringList);


        listView_list_bill.setAdapter(arrayAdapter);

        return myView;
    }
}
