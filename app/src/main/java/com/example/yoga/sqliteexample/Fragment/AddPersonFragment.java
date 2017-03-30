package com.example.yoga.sqliteexample.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;

/**
 * Created by YOGA on 11/6/2016.
 */
public class AddPersonFragment extends Fragment {
    Button button_add_person_add;
    EditText editText_add_person_name, editText_add_person_email;
    Activity activity;

    public interface AddPersonInterface {
        long createPerson(Person p);
    }

    AddPersonInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                mListener = (AddPersonInterface) activity;
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
        View myView = inflater.inflate(R.layout.fragment_add_person, container, false);
        button_add_person_add = (Button) myView.findViewById(R.id.button_add_person_add);
        editText_add_person_email = (EditText) myView.findViewById(R.id.editText_add_person_email);
        editText_add_person_name = (EditText) myView.findViewById(R.id.editText_add_person_name);

        button_add_person_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Adding a new entry to Person", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Person p = new Person();
                p.setName(editText_add_person_name.getText().toString());
                p.setEmail(editText_add_person_email.getText().toString());
                mListener.createPerson(p);

                // Check if no view has focus:
                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        return myView;
    }

}
