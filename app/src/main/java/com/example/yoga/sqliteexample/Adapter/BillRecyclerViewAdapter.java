package com.example.yoga.sqliteexample.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

import java.util.List;


/**
 * Created by YOGA on 11/15/2016.
 */

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.ViewHolder> {
    private List<String> placeList, dateList;
    private List<Person> payerList;
    private Activity activity;


    public interface BillRecyclerInterface {
        void setBillDetailFragment();
    }

    public BillRecyclerViewAdapter(Activity activity, List<String> placeList, List<String> dateList, List<Person> payerList) {
        this.activity = activity;
        this.placeList = placeList;
        this.dateList = dateList;
        this.payerList = payerList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;
        public TextView textView_line1, textView_line2, textView_line3;
        private Activity activity;
        private BillRecyclerInterface mListener;

        public ViewHolder(LinearLayout v, Activity activity) {
            super(v);
            v.setOnClickListener(this);

            this.activity = activity;
            try {
                mListener = (BillRecyclerInterface) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
            }

            mTitle = (TextView) v.findViewById(R.id.textView_title);
            textView_line1 = (TextView) v.findViewById(R.id.textView_line1);
            textView_line2 = (TextView) v.findViewById(R.id.textView_line2);
            textView_line3 = (TextView) v.findViewById(R.id.textView_line3);
        }

        @Override
        public void onClick(View v) {
            // Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show();
            mListener.setBillDetailFragment();
        }
    }



    @Override
    public BillRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder((LinearLayout) v, activity);
        return vh;
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BillRecyclerViewAdapter.ViewHolder holder, int position) {
        char c = Character.toUpperCase(placeList.get(position).charAt(0));
        holder.mTitle.setText(String.valueOf(c));
        holder.textView_line1.setText(placeList.get(position));
        holder.textView_line2.setText(dateList.get(position));
        holder.textView_line3.setText(payerList.get(position).getName());
    }


}
