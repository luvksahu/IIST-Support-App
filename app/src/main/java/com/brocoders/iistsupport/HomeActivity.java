package com.brocoders.iistsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.brocoders.iistsupport.busNo.BusNoActivity;
import com.brocoders.iistsupport.databinding.ActivityHomeBinding;
import com.brocoders.iistsupport.route.RouteActivity;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.btnFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FacultyActivity.class);
            startActivity(intent);
        });
        binding.btnBus.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BusNoActivity.class);
            startActivity(intent);
        });

    }
}