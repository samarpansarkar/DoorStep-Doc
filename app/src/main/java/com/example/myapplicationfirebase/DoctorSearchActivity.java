package com.example.myapplicationfirebase;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
                    String email = (String) data.get("Email");
                    String phoneNumber = (String) data.get("phonenumber");
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

        //searchbar..
        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchQuery = charSequence.toString().trim();
                searchUsers(searchQuery);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    //searchbar..
// Search bar method
    private void searchUsers(String searchQuery) {
        // Clear the current list of users
        userList.clear();
                // Perform the search on the Firebase database
        databaseReference.orderByChild("specialization")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            // Get the data as a HashMap
                            HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();

                            // Extract the values from the HashMap
                            String name = (String) data.get("name");
                            String email = (String) data.get("Email");
                            String phoneNumber = (String) data.get("phonenumber");
                            String specialization = (String) data.get("specialization");
                            String userId = (String) data.get("userId");
                            String userType = (String) data.get("usertype");

                            // Create a User object and add it to the list
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

