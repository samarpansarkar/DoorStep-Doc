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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PatientSignupActivity extends AppCompatActivity {

    private EditText register_name,register_email,register_password,register_Phone;
    private Button register_button;
    private TextView login_text;
    private FirebaseAuth auth;

    private String userId;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_button = findViewById(R.id.register_button);
        register_name = findViewById(R.id.register_name);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_Phone = findViewById(R.id.register_Phone);
        login_text = findViewById(R.id.login_text);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientSignupActivity.this,LoginActivity.class));
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = register_email.getText().toString().trim();
                String txt_name = register_name.getText().toString().trim();
                String txt_password = register_password.getText().toString().trim();
                String txt_phonenumber = register_Phone.getText().toString().trim();

                if (TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_name)||TextUtils.isEmpty(txt_phonenumber))
                {
                    Toast.makeText(PatientSignupActivity.this, "Empty credentials.", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(PatientSignupActivity.this, "Password too short.", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(txt_email,txt_name,txt_password,txt_phonenumber);
                }
            }
        });
    }

    private void registerUser(String email, String name, String password, String PhoneNumber) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(PatientSignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Get the user ID of the registered user
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        userId = user.getUid();
                    }

                    Toast.makeText(PatientSignupActivity.this, "User Register Successful", Toast.LENGTH_SHORT).show();

                    // Create a User Map so we create a user in the user collection
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("userId", userId); // Add user ID to the map
                    hashMap.put("name", name);
                    hashMap.put("Email", email);
                    hashMap.put("Password", password);
                    hashMap.put("phonenumber", PhoneNumber);
                    hashMap.put("specialization", "null");
                    hashMap.put("userType", "patient");

                    // Save to our firebase database
                    databaseReference.child("Users").child(userId).setValue(hashMap); // Use user ID as child node
                    Intent intent = new Intent(PatientSignupActivity.this, PatientMainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PatientSignupActivity.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}