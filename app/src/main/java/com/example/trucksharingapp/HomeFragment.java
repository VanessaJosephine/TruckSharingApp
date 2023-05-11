package com.example.trucksharingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.trucksharingapp.sqlitehelper.DatabaseHelper;
import com.example.trucksharingapp.sqlitehelper.Truck;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OrderAdapter.ItemClickListener{

    private ArrayList<Truck> truckArrayList;
    private ImageView menuBtn, addBtn;
    private DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrderAdapter orderAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get Trucks
        databaseHelper = new DatabaseHelper(getContext());
        truckArrayList = databaseHelper.getAllTrucks();

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewTrucks);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderAdapter = new OrderAdapter(getActivity(), truckArrayList, this);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(layoutManager);

        // Menu Button
        menuBtn = view.findViewById(R.id.imageView3);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Shows menu dialog
                menuDialog();
            }
        });

        // Add Button
        addBtn = view.findViewById(R.id.imageView4);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Takes user to the New Order page
                Intent intent = new Intent(getContext(), NewOrderActivity.class);
                startActivity(intent);
            }
        });
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
                }
                else if (i == 1)
                {
                    // Takes user to My Account page
                }
                else if (i == 2)
                {
                    // Takes user to My Orders page
                    Intent intent = new Intent(getContext(), MyOrderActivity.class);
                    startActivity(intent);
                }
            }
        });
        // Create / show dialog
        builder.create().show();
    }

    @Override
    public void onItemClick(Truck truck) {
        Fragment fragment = OrderDetailFragment.newInstance(truck.getName(), truck.getSender(), truck.getReceiver(),
                truck.getPickup(), truck.getLocation(), truck.getWeight(),
                truck.getWidth(), truck.getHeight(), truck.getLength());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("home_fragment"));
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}