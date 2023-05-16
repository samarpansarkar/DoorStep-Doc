package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName, textViewName, textViewEmail, textViewNumber, textViewSpecialization, textViewUserID, textViewAddress;

    private CardView CardEditName, CardEditPhoneNumber, CardEditAddress, CardEditSpecialization;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textName = findViewById(R.id.textName);
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewSpecialization = findViewById(R.id.textViewSpecialization);
        textViewUserID = findViewById(R.id.textViewUserID);
        textViewAddress = findViewById(R.id.textViewAddress);

        CardEditName = findViewById(R.id.CardEditName);
        CardEditAddress = findViewById(R.id.CardEditAddress);
        CardEditPhoneNumber  = findViewById(R.id.CardEditPhoneNumber);
        CardEditSpecialization = findViewById(R.id.CardEditSpecialization);

       CardEditName.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(ProfileActivity.this,EditNameActivity.class));
           }
       });

        CardEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditAddressActivity.class));
            }
        });

        CardEditPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditPhonenumberActivity.class));
            }
        });

        CardEditSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditSpecializationActivity.class));
            }
        });


        loadUserProfile();
    }

    private void loadUserProfile() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference userRef = databaseReference.child(userId);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String email = dataSnapshot.child("Email").getValue(String.class);
                        String phoneNumber = dataSnapshot.child("phonenumber").getValue(String.class);
                        String specialization = dataSnapshot.child("specialization").getValue(String.class);
                        String userId = dataSnapshot.child("userId").getValue(String.class);
                        String address = dataSnapshot.child("Address").getValue(String.class);

                        textName.setText("Hey, " + name);


                        textViewName.setText("Name: " + name);
                        textViewEmail.setText("Email: " + email);
                        textViewNumber.setText("Call: " + phoneNumber);
                        textViewSpecialization.setText("specialization: " + specialization);
                        textViewUserID.setText("UserID: " + userId);
                        textViewAddress.setText("Address: " + address);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }

    }
}