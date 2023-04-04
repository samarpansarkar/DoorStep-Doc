package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplicationfirebase.drSearch.Users;
import com.example.myapplicationfirebase.drSearch.userAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorSearchActivity extends AppCompatActivity {
private RecyclerView DrrecyclerView;
private userAdapter adapter;

private ArrayList<Users> usersArrayList;

private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);

        database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference().child("user");

        DrrecyclerView = findViewById(R.id.DrrecyclerView);
        DrrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DrrecyclerView.setAdapter(adapter);

        usersArrayList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new userAdapter(DoctorSearchActivity.this,usersArrayList);
    }
}