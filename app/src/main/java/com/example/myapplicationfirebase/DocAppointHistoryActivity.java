package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocAppointHistoryActivity extends AppCompatActivity {
    private RecyclerView DocAppHisrecyclerView;
    private ApointHistoryAdaptor apointHistoryAdapter;
    private List<ApointHistory> apointHistoryList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appoint_history);

        DocAppHisrecyclerView = findViewById(R.id.DocAppHisrecyclerView);
        DocAppHisrecyclerView.setHasFixedSize(true);
        DocAppHisrecyclerView.setLayoutManager(new LinearLayoutManager(this));



        apointHistoryList = new ArrayList<>();
        apointHistoryAdapter = new ApointHistoryAdaptor(DocAppointHistoryActivity.this, apointHistoryList);
        DocAppHisrecyclerView.setAdapter(apointHistoryAdapter);



        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
           String patientUid = currentUser.getUid();
            String DocUserId = getIntent().getStringExtra("userID");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Appointments").child(currentUser.getUid());
            String key = databaseReference.child("Appointments").push().getKey();

            //Doctor His:
            databaseReference.orderByChild(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    apointHistoryList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap<String, String> apointment = (HashMap<String, String>) snapshot.getValue();
                        if (apointment != null) {
                            String date = apointment.get("date");
                            String time = apointment.get("time");
                            String DocName = apointment.get("name");
                            String DocEmail = apointment.get("email");
                            String DocNumber = apointment.get("phone");
                            String DocUserUid = apointment.get("docUserId");

                            ApointHistory apointHistory = new ApointHistory(date, time, DocName, DocEmail, DocNumber, DocUserUid);
                            apointHistoryList.add(apointHistory);
                        }
                    }
                    apointHistoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle database error
                    Toast.makeText(DocAppointHistoryActivity.this, "database error.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    }