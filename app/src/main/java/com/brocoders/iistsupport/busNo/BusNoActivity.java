package com.brocoders.iistsupport.busNo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.brocoders.iistsupport.R;
import com.brocoders.iistsupport.databinding.ActivityBusNoBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class BusNoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BusAdapter busAdapter;
    ActivityBusNoBinding binding;
    public ArrayList<BusModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_no);

        recyclerView = findViewById(R.id.busRV);
        list = new ArrayList<>();

        list.add(new BusModel("36","Lokesh Yadav", "7869748081"));
        list.add(new BusModel("41","Dinesh", "9754934409"));
        list.add(new BusModel("34","Mukesh", "9171815344"));
        list.add(new BusModel("52","Ghanshyam", "7869567620"));
        list.add(new BusModel("44","Vijay Malviya", "9691185640"));
        list.add(new BusModel("55","Santosh", "9424594601"));
        list.add(new BusModel("28","Salim Bhopali", "9009792802"));
        list.add(new BusModel("32","Mohan", "8827363998"));
        list.add(new BusModel("53","Jayram Chouhan", "9977560726"));
        list.add(new BusModel("29","Ghanshyam", "7869540199"));
        list.add(new BusModel("27","Bharat", "9039261011"));
        list.add(new BusModel("35","Sunil", "9826250407"));
        list.add(new BusModel("54","Madhav", "9993432798"));
        list.add(new BusModel("51","Anup", "9644009146"));
        list.add(new BusModel("43","Mahesh", "9754181983"));
        list.add(new BusModel("39","Ashok", "9661642960"));
        list.add(new BusModel("25","Rajesh", "9993681204"));
        list.add(new BusModel("48","Rahul Pawar", "9926081540"));
        list.add(new BusModel("13","Hariom", "7999039349"));
        list.add(new BusModel("33","Omprakash", "9826905808"));
        list.add(new BusModel("31","Rajendra", "9179278274"));
        list.add(new BusModel("30","Nitin Danke", "6264811081"));
        list.add(new BusModel("38","Sachin", "9926678451"));
        list.add(new BusModel("26","Sushil", "7869273927"));
        list.add(new BusModel("37","Rahul Rao", "9165151573"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        BusAdapter adapter = new BusAdapter(list, this);
        recyclerView.setAdapter(adapter);


    }
    // for search in faculty activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

//    private void txtSearch(String str){
//        FirebaseRecyclerOptions<BusModel> options =
//                new FirebaseRecyclerOptions.Builder<BusModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Bus Route").orderByChild("location"), BusModel.class)
//                        .build();
//
//
//        busAdapter = new BusAdapter(options);
//        busAdapter.startListening();
//        recyclerView.setAdapter(busAdapter);
//    }
}
