package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DoctorSignupActivity extends AppCompatActivity {

    private EditText doc_register_name,doc_register_Phone,doc_register_email,doc_register_password,doc_register_specialization;
    private Button doc_register_button;
    private TextView doc_login_text;

    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        doc_register_name = findViewById(R.id.doc_register_name);
        doc_register_Phone = findViewById(R.id.doc_register_Phone);
        doc_register_email = findViewById(R.id.doc_register_email);
        doc_register_password = findViewById(R.id.doc_register_password);
        doc_register_specialization = findViewById(R.id.doc_register_specialization);
        doc_register_button = findViewById(R.id.doc_register_button);
        doc_login_text = findViewById(R.id.doc_login_text);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        doc_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorSignupActivity.this,LoginActivity.class));
            }
        });

        doc_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = doc_register_email.getText().toString();
                String name = doc_register_name.getText().toString();
                String password = doc_register_password.getText().toString();
                String phone = doc_register_Phone.getText().toString();
                String specialization = doc_register_specialization.getText().toString();


                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(specialization))
                {
                    Toast.makeText(DoctorSignupActivity.this, "Empty credentials.", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(DoctorSignupActivity.this, "Password too short.", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(email,name,password,phone,specialization);
                }
            }
        });

    }

    private void registerUser(String email, String name, String password, String phone, String specialization) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = auth.getCurrentUser().getUid(); // Get the user ID of the registered user
                    Toast.makeText(DoctorSignupActivity.this, "User Register Successful", Toast.LENGTH_SHORT).show();

                    // Create a User Map so we create a user in the user collection
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("userId", userId); // Add user ID to the user data
                    hashMap.put("name", name);
                    hashMap.put("Email", email);
                    hashMap.put("Password", password);
                    hashMap.put("phonenumber", phone);
                    hashMap.put("specialization", specialization);
                    hashMap.put("userType", "doctor");

                    // Save to our firebase database
                    databaseReference.child("Users").child(userId).setValue(hashMap); // Store the user data with user ID as key

                    startActivity(new Intent(DoctorSignupActivity.this, PatientMainActivity.class));
                    finish();
                } else {
                    Toast.makeText(DoctorSignupActivity.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}