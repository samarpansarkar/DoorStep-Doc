package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditSpecializationActivity extends AppCompatActivity {

    private EditText editTextSpecialization;

    private Button buttonSave;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specialization); firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        editTextSpecialization = findViewById(R.id.editTextSpecialization);
        buttonSave = findViewById(R.id.buttonSaveSpecialization);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        String specialization = editTextSpecialization.getText().toString().trim();

      if (specialization.isEmpty()) {
            Toast.makeText(this, "Please enter a specialization", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference userRef = databaseReference.child(userId);

            userRef.child("specialization").setValue(specialization);

            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditSpecializationActivity.this,ProfileActivity.class));
            finish();
        }
    }}