package com.example.trucksharingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailFragment extends Fragment {

    TextView name, sender, receiver, time, location, weight, width, height, length;
    ImageView menuBtn;
    Button callBtn;
    private static final String NAME = "name";
    private static final String SENDER = "sender";
    private static final String RECEIVER = "receiver";
    private static final String TIME = "time";
    private static final String LOCATION = "location";
    private static final String WEIGHT = "weight";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String LENGTH = "length";
    private String Name, Sender, Receiver, Time, Location, Weight, Width, Height, Length;
    public OrderDetailFragment() {
        // Required empty public constructor
    }

    public static OrderDetailFragment newInstance(String name, String sender, String receiver, String time, String location, String weight, String width, String height, String length) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(SENDER, sender);
        args.putString(RECEIVER, receiver);
        args.putString(TIME, time);
        args.putString(LOCATION, location);
        args.putString(WEIGHT, weight);
        args.putString(HEIGHT, height);
        args.putString(WIDTH, width);
        args.putString(LENGTH, length);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Name = getArguments().getString(NAME);
            Sender = getArguments().getString(SENDER);
            Receiver = getArguments().getString(RECEIVER);
            Time = getArguments().getString(TIME);
            Location = getArguments().getString(LOCATION);
            Weight = getArguments().getString(WEIGHT);
            Width = getArguments().getString(WIDTH);
            Height = getArguments().getString(HEIGHT);
            Length = getArguments().getString(LENGTH);
        } else Toast.makeText(getContext(), "Empty", Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        // FindViewById
        name = view.findViewById(R.id.textViewDetailName);
        sender = view.findViewById(R.id.textViewDetailSender);
        receiver = view.findViewById(R.id.textViewDetailReceiver);
        time = view.findViewById(R.id.textViewDetailPickup);
        location = view.findViewById(R.id.textViewDetailLocation);
        weight = view.findViewById(R.id.textViewDetailWeight);
        height = view.findViewById(R.id.textViewDetailHeight);
        length = view.findViewById(R.id.textViewDetailLength);
        width = view.findViewById(R.id.textViewDetailWidth);

        menuBtn = view.findViewById(R.id.imageViewMenu);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog();
            }
        });

        callBtn = view.findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0404582772"));
                startActivity(myIntent3);
            }
        });

        // Set Values
        name.setText(Name);
        sender.setText(sender.getText() + Sender);
        receiver.setText(receiver.getText() + Receiver);
        time.setText(time.getText() + Time);
        location.setText(location.getText() + Location);
        weight.setText(weight.getText() + Weight);
        width.setText(width.getText() + Width);
        height.setText(height.getText() + Height);
        length.setText(length.getText() + Length);

        return view;
    }
    private void menuDialog() {
        // Option to display dialog
        String[] options = { "Home", "My Account", "My Orders" };
        // Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) // Handle clicks
                {
                    // Takes user to home (current page)
                    Intent intent = new Intent(getContext(), HomeFragment.class);
                    startActivity(intent);
                }
                else if (i == 1)
                {
                    // Takes user to My Account page
                }
                else if (i == 2)
                {
                    // Takes user to My Orders page
                    Intent intent2 = new Intent(getContext(), MyOrderActivity.class);
                    startActivity(intent2);
                }
            }
        });
        // Create / show dialog
        builder.create().show();
    }
}