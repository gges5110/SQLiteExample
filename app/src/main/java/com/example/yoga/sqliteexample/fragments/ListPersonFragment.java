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

import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOGA on 11/7/2016.
 */
public class ListPersonFragment extends Fragment {
    ListView listView_list_person;

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
        listView_list_person = (ListView) myView.findViewById(R.id.listView_list_person);
        List<Person> personList = mListener.getAllPeople();

        ArrayList<String> personStringList = new ArrayList<>();
        for (int i = 0; i < personList.size(); ++i) {
            Person p = personList.get(i);
            personStringList.add(p.getName() + ", " + p.getEmail());
        }

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(myView.getContext(), R.layout.listview_layout, personStringList);
        // Set The Adapter
        listView_list_person.setAdapter(arrayAdapter);

        return myView;
    }
}
