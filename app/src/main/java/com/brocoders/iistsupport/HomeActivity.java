package com.brocoders.iistsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.brocoders.iistsupport.busNo.BusNoActivity;
import com.brocoders.iistsupport.databinding.ActivityHomeBinding;
import com.brocoders.iistsupport.notices.notice.NoticeActivity;
import com.brocoders.iistsupport.route.RouteActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.btnFaculty.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, FacultyActivity.class)));

        binding.btnBus.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, BusNoActivity.class)));

        binding.btnWebsite.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, WebViewActivity.class)));

        binding.btnNotices.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, NoticeActivity.class)));


        binding.btnUser.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });
    }
}