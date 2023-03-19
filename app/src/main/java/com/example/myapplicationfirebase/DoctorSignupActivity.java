package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DoctorSignupActivity extends AppCompatActivity {

    private EditText doc_register_name,doc_register_Phone,doc_register_email,doc_register_password;
    private Button doc_register_button;
    private TextView doc_login_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        doc_register_name = findViewById(R.id.doc_register_name);
        doc_register_Phone = findViewById(R.id.doc_register_Phone);
        doc_register_email = findViewById(R.id.doc_register_email);
        doc_register_password = findViewById(R.id.doc_register_password);
        doc_register_button = findViewById(R.id.doc_register_button);
        doc_login_text = findViewById(R.id.doc_login_text);

        doc_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorSignupActivity.this,LoginActivity.class));
            }
        });

    }
}