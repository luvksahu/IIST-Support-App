package com.brocoders.iistsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.adapter.FacultyAdapter;
import com.brocoders.iistsupport.databinding.ActivityFacultyBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyActivity extends AppCompatActivity {
    FacultyAdapter facultyAdapter;
    ActivityFacultyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.facultyRv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FacultyModel> options =
                new FirebaseRecyclerOptions.Builder<FacultyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty"), FacultyModel.class)
                        .build();

        facultyAdapter = new FacultyAdapter(options);
        binding.facultyRv.setAdapter(facultyAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        facultyAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        facultyAdapter.stopListening();
    }
}
