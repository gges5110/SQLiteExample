package com.example.yoga.sqliteexample.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yoga.sqliteexample.Model.Bill;
import com.example.yoga.sqliteexample.Model.Person;
import com.example.yoga.sqliteexample.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by YOGA on 11/6/2016.
 */
public class AddBillFragment extends Fragment implements OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Spinner spinner_add_bill;
    Button button_add_bill_pickdate, button_add_bill_add, button_add_bill_picktime;
    EditText editText_add_bill;
    private Date date = null, time = null;
    List<Person> personList;
    int payer_id = -1;

    public interface AddBillInterface {
        List<Person> getAllPeople();
        long createBill(Bill bill);
    }

    AddBillInterface mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                mListener = (AddBillInterface) activity;
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
        final View myView = inflater.inflate(R.layout.fragment_add_bill, container, false);
        spinner_add_bill = (Spinner) myView.findViewById(R.id.spinner_add_bill);
        button_add_bill_pickdate = (Button) myView.findViewById(R.id.button_add_bill_pickdate);
        button_add_bill_picktime = (Button) myView.findViewById(R.id.button_add_bill_picktime);
        button_add_bill_add = (Button) myView.findViewById(R.id.button_add_bill_add);
        editText_add_bill = (EditText) myView.findViewById(R.id.editText_add_bill);

        personList = mListener.getAllPeople();

        ArrayList<String> personStringList = new ArrayList<>();
        for (int i = 0; i < personList.size(); ++i) {
            Person p = personList.get(i);
            personStringList.add(p.getName() + ", " + p.getEmail());
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(myView.getContext(), android.R.layout.simple_spinner_item, personStringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_add_bill.setAdapter(adapter);
        spinner_add_bill.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payer_id = personList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_add_bill_pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddBillFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        button_add_bill_picktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        AddBillFragment.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        now.get(Calendar.SECOND),
                        true
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        button_add_bill_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if no view has focus:
                if (myView != null) {
                    InputMethodManager imm = (InputMethodManager) myView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(myView.getWindowToken(), 0);
                }

                if (date == null || time == null || payer_id == -1 || editText_add_bill.getText().toString().equals("")) {
                    Toast.makeText(myView.getContext(), "Please select date and payer.", Toast.LENGTH_SHORT).show();
                } else {
                    Bill bill = new Bill();
                    bill.setPlace(editText_add_bill.getText().toString());
                    date.setHours(time.getHours());
                    date.setMinutes(time.getMinutes());
                    date.setSeconds(time.getSeconds());
                    bill.setDate(date);
                    bill.setPayer(payer_id);
                    if(mListener.createBill(bill) == -1) {
                        Toast.makeText(myView.getContext(), "Error occurred when inserting into database.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(myView.getContext(), "Bill added.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return myView;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        date = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        button_add_bill_pickdate.setText(dateFormat.format(date));
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar c = new GregorianCalendar(0, 0, 0, hourOfDay, minute, second);
        time = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        button_add_bill_picktime.setText(dateFormat.format(time));
    }
}
