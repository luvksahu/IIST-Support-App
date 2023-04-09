package com.brocoders.iistsupport.notices.noticeImage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.brocoders.iistsupport.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class NoticeImageActivity extends AppCompatActivity {

    private String message;
    ImageView rImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_image);

        Intent intent = getIntent();
        message = intent.getStringExtra("mess");

        rImage = findViewById(R.id.noticeImg);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();

        DatabaseReference getImage = databaseReference.child("Notice Image").child(message);

        getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        String link = dataSnapshot.getValue(
                                String.class);
//                        HashMap<String, Object> linkMap = (HashMap<String,Object>) dataSnapshot.getValue();
//                        String link = (String) linkMap.get("image_url");

                        Picasso.get().load(link).into(rImage);
                    }

                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast
                                .makeText(NoticeImageActivity.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });



    }
}