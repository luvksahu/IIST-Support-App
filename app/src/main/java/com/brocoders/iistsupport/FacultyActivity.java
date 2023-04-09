package com.brocoders.iistsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.adapter.FacultyAdapter;
import com.brocoders.iistsupport.databinding.ActivityFacultyBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class FacultyActivity extends AppCompatActivity {
    FacultyAdapter adapter;
    FirebaseDatabase database;
    ActivityFacultyBinding binding;
    RecyclerView recyclerView;
    ArrayList<FacultyModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Find a mentor");

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.faculty_rv);
        binding.facultyRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FacultyAdapter(list, getBaseContext());
        binding.facultyRv.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        database.getReference().child("Faculty")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            FacultyModel model = dataSnapshot.getValue(FacultyModel.class);
                            list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FacultyActivity.this, "Some error encountered", Toast.LENGTH_SHORT).show();
                    }
                });
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
                txtSearch(query.toLowerCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query.toLowerCase());
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){


        database.getReference().child("Faculty")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            FacultyModel model = dataSnapshot.getValue(FacultyModel.class);
                            if(model != null && model.getExpertise().toLowerCase().contains(str))
                                list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FacultyActivity.this, "Some error encountered", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
