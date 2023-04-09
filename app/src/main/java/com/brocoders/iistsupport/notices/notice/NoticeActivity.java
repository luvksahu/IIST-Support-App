package com.brocoders.iistsupport.notices.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.brocoders.iistsupport.Classes.RecyclerItemClickListener;
import com.brocoders.iistsupport.R;
import com.brocoders.iistsupport.busNo.BusAdapter;
import com.brocoders.iistsupport.busNo.BusModel;
import com.brocoders.iistsupport.busNo.BusNoActivity;
import com.brocoders.iistsupport.databinding.ActivityNoticeBinding;
import com.brocoders.iistsupport.notices.noticeImage.NoticeImageActivity;
import com.brocoders.iistsupport.route.RouteActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoticeAdapter noticeAdapter;
    ActivityNoticeBinding binding;
    public ArrayList<NoticeModel> list;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setTitle("Notices");

        database = FirebaseDatabase.getInstance();
        recyclerView = findViewById(R.id.noticeRV);
        list = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        noticeAdapter = new NoticeAdapter(list, this);
        recyclerView.setAdapter(noticeAdapter);

        database.getReference().child("Notices")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //list.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            NoticeModel model = dataSnapshot.getValue(NoticeModel.class);
                            list.add(model);
                        }
                        noticeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(NoticeActivity.this, "Some error encountered", Toast.LENGTH_SHORT).show();
                    }
                });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(NoticeActivity.this, recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(NoticeActivity.this, NoticeImageActivity.class);
                                intent.putExtra("mess", list.get(position).message);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
        }));







    }
}