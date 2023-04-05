package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorSearchActivity extends AppCompatActivity {

    //FirebaseAuth auth;
    RecyclerView DrrecyclerView;
    UserAdpter adapter;
    FirebaseDatabase database;
    ArrayList<Users> usersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);

        database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference().child("User");

        usersArrayList = new ArrayList<>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DrrecyclerView = findViewById(R.id.DrrecyclerView);
        DrrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdpter(DoctorSearchActivity.this, usersArrayList);
        DrrecyclerView.setAdapter(adapter);


    }
}