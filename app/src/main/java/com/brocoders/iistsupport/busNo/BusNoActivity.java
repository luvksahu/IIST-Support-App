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

        list.add(new BusModel("39","Mahesh Kumawat", "9754181983"));
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
