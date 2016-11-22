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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yoga.sqliteexample.Adapter.PersonRecyclerViewAdapter;
import com.example.yoga.sqliteexample.Decoration.DividerItemDecoration;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOGA on 11/7/2016.
 */
public class ListPersonFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public interface ListPersonInterface {
        List<Person> getAllPeople();
    }

    ListPersonInterface mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                mListener = (ListPersonInterface) activity;
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
        View myView = inflater.inflate(R.layout.fragment_list_person, container, false);
        drawVerticalLines(myView);

        List<Person> personList = mListener.getAllPeople();

        List<String> emailList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < personList.size(); ++i) {
            Person p = personList.get(i);
            emailList.add(p.getEmail());
            nameList.add(p.getName());
        }

        RecyclerView.Adapter mAdapter = new PersonRecyclerViewAdapter(getActivity(), nameList, emailList);
        mRecyclerView.setAdapter(mAdapter);

        return myView;
    }

    public void drawVerticalLines(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listView_list_person);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //add ItemDecoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
    }
}
