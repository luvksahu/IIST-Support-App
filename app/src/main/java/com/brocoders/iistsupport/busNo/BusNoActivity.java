package com.brocoders.iistsupport.busNo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.brocoders.iistsupport.R;

import java.util.ArrayList;

public class BusNoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_no);

        recyclerView = findViewById(R.id.busRV);
        ArrayList<BusModel> list = new ArrayList<>();

        list.add(new BusModel("1","Anthony Gonjalvis", "7477253011"));
        list.add(new BusModel("2","Raju Pandey", "9846283006"));
        list.add(new BusModel("3","Salman Khan", "9846283006"));
        list.add(new BusModel("4","Karan Johar", "9846283006"));
        list.add(new BusModel("5","Ananya Pandey", "9846283006"));
        list.add(new BusModel("6","Saif Khan", "9846283006"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        BusAdapter adapter = new BusAdapter(list, this);
        recyclerView.setAdapter(adapter);

    }
}