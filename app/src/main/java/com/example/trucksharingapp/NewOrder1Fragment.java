package com.example.trucksharingapp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.trucksharingapp.sqlitehelper.DatabaseHelper;
import com.example.trucksharingapp.sqlitehelper.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class NewOrder1Fragment extends Fragment {
    private EditText date, time, receiver, location;
    private Button nextBtn;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    public NewOrder1Fragment() {
        // Required empty public constructor
    }

    public static NewOrder1Fragment newInstance() {
        NewOrder1Fragment fragment = new NewOrder1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_order1, container, false);
        // Initialization
        Initialization(view);
        // Date Picker
        date.setShowSoftInputOnFocus(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calender class's instance and get current date, month and year from calendar
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // Date picker dialog
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        // Time Picker
        time.setShowSoftInputOnFocus(false);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calender class's instance and get current hour and minute
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        // Next button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Makes sure EditText is not empty
                if (!date.getText().toString().isEmpty() ||
                        !time.getText().toString().isEmpty() ||
                        !receiver.getText().toString().isEmpty() ||
                        !location.getText().toString().isEmpty()
                )
                {
                    // SharedPreferences
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEditor = sharedPreferences.edit();
                    myEditor.putString("receiver", receiver.getText().toString());
                    myEditor.putString("date", date.getText().toString());
                    myEditor.putString("time", time.getText().toString());
                    myEditor.putString("location", location.getText().toString());
                    myEditor.apply();

                    Fragment fragment = NewOrder2Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("order_fragment"));
                    transaction.add(R.id.frame_container2, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else Toast.makeText(getContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    public void Initialization(View view) {
        // findViewById
        date = view.findViewById(R.id.editTextDate);
        time = view.findViewById(R.id.editTextTime);
        nextBtn = view.findViewById(R.id.button4);
        receiver = view.findViewById(R.id.editTextText4);
        location = view.findViewById(R.id.editTextLocation);
    }
}