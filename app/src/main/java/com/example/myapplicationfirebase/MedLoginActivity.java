package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MedLoginActivity extends AppCompatActivity {

    private EditText login_email ,login_password;
    private Button login_button;
    private TextView signup_text, login_contactus;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_login);

        login_button = findViewById(R.id.med_login_button);
        login_email = findViewById(R.id.med_login_email);
        login_password = findViewById(R.id.med_login_password);
        signup_text = findViewById(R.id.med_signup_text);

        auth = FirebaseAuth.getInstance();

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedLoginActivity.this,StartActivity.class));
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = login_email.getText().toString().trim();
                String txt_password = login_password.getText().toString().trim();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MedLoginActivity.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txt_email , txt_password);
                }
            }
        });

    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MedLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedLoginActivity.this, MedicineMainActivity.class));
                finish();
            }
        });

    }
}