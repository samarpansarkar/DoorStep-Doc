package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookAppointmentActivity2 extends AppCompatActivity {

    private Button book_back_button,book_appointment_button;
    private EditText doctor_name,doctor_phone,doctor_email,doctor_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment2);

        book_appointment_button = findViewById(R.id.book_appointment_button);
        book_back_button = findViewById(R.id.book_back_button);
        doctor_name = findViewById(R.id.patient_name);
        doctor_phone = findViewById(R.id.patient_phone);
        doctor_email = findViewById(R.id.patient_email);
        doctor_address = findViewById(R.id.patient_address);

        doctor_name.setKeyListener(null);
        doctor_phone.setKeyListener(null);
        doctor_email.setKeyListener(null);
        doctor_address.setKeyListener(null);

        String name = getIntent().getStringExtra("Name");
        String phone = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");

        doctor_name.setText(name);
        doctor_phone.setText(phone);
        doctor_email.setText(email);
        doctor_address.setText(address);



    }
}