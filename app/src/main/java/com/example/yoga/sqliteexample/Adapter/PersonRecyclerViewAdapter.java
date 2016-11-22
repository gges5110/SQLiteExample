package com.example.yoga.sqliteexample.Adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;


import java.util.List;

/**
 * Created by YOGA on 11/20/2016.
 */

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder> {
    Activity activity;
    List<String> nameList, emailList;


    public PersonRecyclerViewAdapter(Activity activity, List<String> nameList, List<String> emailList) {
        this.activity = activity;
        this.nameList = nameList;
        this.emailList = emailList;
    }

    @Override
    public PersonRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_listitem, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new PersonRecyclerViewAdapter.ViewHolder((LinearLayout) v, activity);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.person_listitem_textView_email.setText(emailList.get(position));
        holder.person_listitem_textView_name.setText(nameList.get(position));
    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatActivity appCompatActivity;
        TextView person_listitem_textView_name, person_listitem_textView_email;
        public ViewHolder(LinearLayout itemView, Activity activity) {
            super(itemView);
            if (activity instanceof  AppCompatActivity) {
                this.appCompatActivity = (AppCompatActivity) activity;
            }

            person_listitem_textView_name = (TextView) itemView.findViewById(R.id.person_listitem_textView_name);
            person_listitem_textView_email = (TextView) itemView.findViewById(R.id.person_listitem_textView_email);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
