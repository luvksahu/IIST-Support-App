package com.brocoders.iistsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.adapter.FacultyAdapter;
import com.brocoders.iistsupport.databinding.ActivityFacultyBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyActivity extends AppCompatActivity {
    FacultyAdapter facultyAdapter;
    ActivityFacultyBinding binding;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.faculty_rv);
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

    // for search in faculty activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<FacultyModel> options =
                new FirebaseRecyclerOptions.Builder<FacultyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty").orderByChild("expertise"), FacultyModel.class)
                        .build();

        facultyAdapter = new FacultyAdapter(options);
        facultyAdapter.startListening();
        recyclerView.setAdapter(facultyAdapter);
        // upar vale code ko dhekna pahale hi likhe hai
        // .startAt(str).endAt(str+"~")

    }




}
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // below line is to get our inflater
//        MenuInflater inflater = getMenuInflater();
//
//        // inside inflater we are inflating our menu file.
//        inflater.inflate(R.menu.search_menu, menu);
//
//        // below line is to get our menu item.
//        MenuItem searchItem = menu.findItem(R.id.actionSearch);
//
//        // getting search view of our item.
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        // below line is to call set on query text listener method.
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // inside on query text change method we are
//                // calling a method to filter our recycler view.
//                filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }