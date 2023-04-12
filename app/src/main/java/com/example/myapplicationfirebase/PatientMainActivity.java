package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientMainActivity extends AppCompatActivity {

    private Button doc_booking_button;
    private ImageView logOut;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);


        doc_booking_button = findViewById(R.id.doc_booking_button);
        logOut = findViewById(R.id.logOut);




        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(PatientMainActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PatientMainActivity.this, StartActivity.class));
                finish();
            }
        });

        doc_booking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActivity.this,DoctorSearchActivity.class));
            }
        });
    }
}