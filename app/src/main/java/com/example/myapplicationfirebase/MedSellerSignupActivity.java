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

public class MedSellerSignupActivity extends AppCompatActivity {

    private EditText med_register_name,med_register_Phone,med_register_email,med_register_address,med_register_password;

    private TextView med_login_text;

    private Button med_register_button;

    private FirebaseAuth auth;

    private String userId;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_seller_signup);

        med_register_name = findViewById(R.id.med_register_name);
        med_register_Phone = findViewById(R.id.med_register_Phone);
        med_register_email = findViewById(R.id.med_register_email);
        med_register_address = findViewById(R.id.med_register_address);
        med_register_password = findViewById(R.id.med_register_password);
        med_login_text = findViewById(R.id.med_login_text);
        med_register_button = findViewById(R.id.med_register_button);


        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();


        med_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedSellerSignupActivity.this,MedLoginActivity.class));
                finish();
            }
        });

       med_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = med_register_email.getText().toString().trim();
                String txt_name = med_register_name.getText().toString().trim();
                String txt_password = med_register_password.getText().toString().trim();
                String txt_phonenumber = med_register_Phone.getText().toString().trim();
                String txt_address = med_register_address.getText().toString().trim();

                if (TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_name)||TextUtils.isEmpty(txt_phonenumber))
                {
                    Toast.makeText(MedSellerSignupActivity.this, "Empty credentials.", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(MedSellerSignupActivity.this, "Password too short.", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(txt_email,txt_name,txt_password,txt_phonenumber,txt_address);
                }
            }
        });

    }

    private void registerUser(String email, String name, String password, String PhoneNumber, String address) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MedSellerSignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Get the user ID of the registered user
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        userId = user.getUid();
                    }

                    Toast.makeText(MedSellerSignupActivity.this, "User Register Successful", Toast.LENGTH_SHORT).show();

                    // Create a User Map so we create a user in the user collection
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("userId", userId); // Add user ID to the map
                    hashMap.put("Name", name);
                    hashMap.put("Email", email);
                    hashMap.put("Password", password);
                    hashMap.put("Phonenumber", PhoneNumber);
                    hashMap.put("Address", address);

                    // Save to our firebase database
                    databaseReference.child("MedSeller").child(userId).setValue(hashMap); // Use user ID as child node
                    Intent intent = new Intent(MedSellerSignupActivity.this, MedicineMainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MedSellerSignupActivity.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}