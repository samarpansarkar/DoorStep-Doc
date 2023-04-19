package com.example.myapplicationfirebase;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorSearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> userList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);

        recyclerView = findViewById(R.id.DrrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        adapter = new UserAdapter(DoctorSearchActivity.this, userList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        //Query query = databaseReference.orderByChild("userType").equalTo("doctor");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   // Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    // Get the data as a HashMap
                    HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();

                    // Extract the values from the HashMap
                    String name = (String) data.get("name");
                    String email = (String) data.get("email");
                    String phoneNumber = (String) data.get("phoneNumber");
                    String specialization = (String) data.get("specialization");
                    String userId = (String) data.get("userId");
                    String userType = (String) data.get("usertype");

                    // Create a Doctor object and add it to the list
                    User user = new User(name, email, phoneNumber, specialization, userId, userType);

                    userList.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // Handle database error
                Toast.makeText(DoctorSearchActivity.this, "database error.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

