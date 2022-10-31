package com.brocoders.iistsupport.route;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.brocoders.iistsupport.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brocoders.iistsupport.databinding.ActivityBusRouteBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RouteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RouteAdapter busAdapter;
    ActivityBusRouteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusRouteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.routeRV);
        binding.routeRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RouteModel> options =
                new FirebaseRecyclerOptions.Builder<RouteModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Bus Route").child("R1"), RouteModel.class)
                        .build();

        busAdapter = new RouteAdapter(options);
        binding.routeRV.setAdapter(busAdapter);
    }
    @Override

    protected void onStart() {
        super.onStart();
        busAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        busAdapter.stopListening();
    }
}