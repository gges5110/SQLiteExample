package com.example.yoga.sqliteexample.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.BillItem;
import com.example.yoga.sqliteexample.Model.Item;
import com.example.yoga.sqliteexample.R;

/**
 * Created by YOGA on 11/7/2016.
 */
public class AddItemFragment extends Fragment {
    private TextView textView_addItem_title;
    private EditText editText_addItem_price, editText_addItem_itemName;
    private Button button_addItem_add;
    private AddItemInterface mListener;
    private int bill_id = -1;

    public void setBillID(int bill_id) {
        this.bill_id = bill_id;
    }

    public interface AddItemInterface {
        long createItem(Item item);

        long editItem(Item item);

        long createBillItem(BillItem billItem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            try {
                mListener = (AddItemInterface) activity;
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
        View myView = inflater.inflate(R.layout.fragment_add_item, container, false);
        textView_addItem_title = (TextView) myView.findViewById(R.id.textView_addItem_title);
        editText_addItem_price = (EditText) myView.findViewById(R.id.editText_addItem_price);
        editText_addItem_itemName = (EditText) myView.findViewById(R.id.editText_addItem_itemName);
        button_addItem_add = (Button) myView.findViewById(R.id.button_addItem_add);

        button_addItem_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Do some sanity check on the input values here.

                // Create new item.
                Item item = new Item();
                item.setItemName(editText_addItem_itemName.getText().toString());
                item.setPrice(Integer.valueOf(editText_addItem_price.getText().toString()));
                long item_id = mListener.createItem(item);

                // TODO create BillItem and display in BillDetailFragment
                BillItem billItem = new BillItem();
                billItem.setBill_id(bill_id);
                billItem.setItem_id((int) item_id);
                mListener.createBillItem(billItem);
            }
        });
        return myView;
    }
}
