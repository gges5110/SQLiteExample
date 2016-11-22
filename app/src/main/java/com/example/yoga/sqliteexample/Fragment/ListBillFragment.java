package com.example.yoga.sqliteexample.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoga.sqliteexample.Adapter.BillRecyclerViewAdapter;
import com.example.yoga.sqliteexample.Decoration.DividerItemDecoration;
import com.example.yoga.sqliteexample.Model.Bill;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by YOGA on 11/7/2016.
 */
public class ListBillFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        drawVerticalLines(myView);

        // specify an adapter (see also next example)
        List<Bill> billList = mListener.getAllBills();
        List<String> placeList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<Person> payerList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        for (int i = 0; i < billList.size(); ++i) {
            Bill b = billList.get(i);
            Person p = mListener.getPerson(b.getPayer());
            placeList.add(b.getPlace());
            dateList.add(dateFormat.format(b.getDate()));
            payerList.add(p);
        }

        mAdapter = new BillRecyclerViewAdapter(getActivity(), placeList, dateList, payerList);
        mRecyclerView.setAdapter(mAdapter);

        return myView;
    }

    public void drawVerticalLines(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //add ItemDecoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
    }


}
