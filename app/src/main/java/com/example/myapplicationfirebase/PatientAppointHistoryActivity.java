package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
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

public class PatientAppointHistoryActivity extends AppCompatActivity {

    private RecyclerView appHisRecyclerView;
    private AppointHistoryAdaptor appointHistoryAdapter;
    private ApointHistoryAdaptor apointHistoryAdapter;

    private List<AppointHistory> appointHistoryList;
    private List<ApointHistory> apointHistoryList;

    private DatabaseReference databaseReference;

    private String patientUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appoint_history);

        appHisRecyclerView = findViewById(R.id.AppHisrecyclerView);
        appHisRecyclerView.setHasFixedSize(true);
        appHisRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        appointHistoryList = new ArrayList<>();
        appointHistoryAdapter = new AppointHistoryAdaptor(PatientAppointHistoryActivity.this, appointHistoryList);
        appHisRecyclerView.setAdapter(appointHistoryAdapter);



        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            patientUid = currentUser.getUid();
            String DocUserId = getIntent().getStringExtra("userID");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Appointments").child(currentUser.getUid());
            String key = databaseReference.child("Appointments").push().getKey();

            //Doctor His:
            databaseReference.orderByChild(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    appointHistoryList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap<String, String> appointment = (HashMap<String, String>) snapshot.getValue();
                        if (appointment != null) {
                            String date = appointment.get("date");
                            String time = appointment.get("time");
                            String PatientName = appointment.get("patientName");
                            String PatientEmail = appointment.get("patientEmail");
                            String PatientNumber = appointment.get("patientNumber");
                            String PatientUserUid = appointment.get("patientUserId");

                            AppointHistory appointHistory = new AppointHistory(date, time, PatientName, PatientEmail, PatientNumber, PatientUserUid);
                            appointHistoryList.add(appointHistory);
                        }
                    }
                    appointHistoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle database error
                    Toast.makeText(PatientAppointHistoryActivity.this, "database error.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}