package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookAppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointUserAdaptor appointUserAdaptor;
    private List<AppointUser> appointUserList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        recyclerView = findViewById(R.id.AppointRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appointUserList = new ArrayList<AppointUser>();
        appointUserAdaptor = new AppointUserAdaptor(BookAppointmentActivity.this, appointUserList);
        recyclerView.setAdapter(appointUserAdaptor);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        //Query query = databaseReference.orderByChild("userType").equalTo("doctor");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointUserList.clear();
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
                    String address = (String) data.get("Address");

                    // Create a Doctor object and add it to the list
                    AppointUser appointUser = new AppointUser(name, email, phoneNumber, specialization, userId, userType,address);

                    appointUserList.add(appointUser);
                }
                appointUserAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // Handle database error
                Toast.makeText(BookAppointmentActivity.this, "database error.", Toast.LENGTH_SHORT).show();
            }
        });
        //searchbar..
        EditText searchEditText = findViewById(R.id.searchEditTextAppoint);
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
    private void searchUsers(String searchQuery) {
        // Clear the current list of users
        appointUserList.clear();
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
                            String userType = (String) data.get("userType");
                            String address = (String) data.get("Address") ;

                            // Create a User object and add it to the list
                            AppointUser appointUser = new AppointUser(name, email, phoneNumber, specialization, userId, userType,address);
                            appointUserList.add(appointUser);
                        }
                        appointUserAdaptor.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle database error
                        Toast.makeText(BookAppointmentActivity.this, "database error.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
