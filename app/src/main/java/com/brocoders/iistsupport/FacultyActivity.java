package com.brocoders.iistsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.adapter.FacultyAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FacultyAdapter facultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        recyclerView = (RecyclerView) findViewById(R.id.faculty_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FacultyModel> options =
                new FirebaseRecyclerOptions.Builder<FacultyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty"), FacultyModel.class)
                        .build();

        facultyAdapter = new FacultyAdapter(options);
        recyclerView.setAdapter(facultyAdapter);
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
