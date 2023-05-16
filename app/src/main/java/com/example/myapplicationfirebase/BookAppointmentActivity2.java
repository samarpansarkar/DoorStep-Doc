package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookAppointmentActivity2 extends AppCompatActivity {

    private Button book_back_button, book_appointment_button;
    private EditText doctor_name, doctor_phone, doctor_email, doctor_address, timeEditText, dateEditText;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment2);

        book_appointment_button = findViewById(R.id.book_appointment_button);
        book_back_button = findViewById(R.id.book_back_button);
        doctor_name = findViewById(R.id.doctor_name);
        doctor_phone = findViewById(R.id.doctor_phone);
        doctor_email = findViewById(R.id.doctor_email);
        doctor_address = findViewById(R.id.doctor_address);
        timeEditText = findViewById(R.id.timeEditText);
        dateEditText = findViewById(R.id.dateEditText);

        book_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity2.this, BookAppointmentActivity.class));
            }
        });

        doctor_name.setKeyListener(null);
        doctor_phone.setKeyListener(null);
        doctor_email.setKeyListener(null);
        doctor_address.setKeyListener(null);

        String name = getIntent().getStringExtra("Name");
        String phone = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String DocUserId = getIntent().getStringExtra("userID");


        // Retrieve the current user from Firebase Authentication
        String PatientUserId = firebaseAuth.getCurrentUser().getUid();
        String PatientEmail = currentUser.getEmail();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users").child(PatientUserId);


        doctor_name.setText(name);
        doctor_phone.setText(phone);
        doctor_email.setText(email);
        doctor_address.setText(address);

        book_appointment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String Date = dateEditText.getText().toString();
                            String Time = timeEditText.getText().toString();
                            String PatientName = snapshot.child("name").getValue(String.class);
                            String PatientNumber = snapshot.child("phonenumber").getValue(String.class);
                            String UserType = snapshot.child("userType").getValue(String.class);
                            String key = usersRef.child("Appointments").push().getKey();

                            if (TextUtils.isEmpty(PatientName)) {
                                Toast.makeText(BookAppointmentActivity2.this, "Please update your profile to book an appointment", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(BookAppointmentActivity2.this, EditProfileActivity.class));
                            } else if (TextUtils.isEmpty(Date) || TextUtils.isEmpty(Time)) {
                                Toast.makeText(BookAppointmentActivity2.this, "Please select a date and time", Toast.LENGTH_SHORT).show();
                            } else {
                                Appoint appoint = new Appoint(PatientName, PatientNumber, PatientEmail, PatientUserId, Date, Time, UserType);

                                String DocUserId = getIntent().getStringExtra("userID");

                                database.getReference().child("Appointments")
                                        .child(DocUserId).child(key).setValue(appoint).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(BookAppointmentActivity2.this, "Appoint Book Successful!!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(BookAppointmentActivity2.this, BookingAppontDoneActivity.class);
                                                finish();
                                                startActivity(intent);
                                            }
                                        }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                //Doctor data from AppointUserAdaptor
                                                String Date = dateEditText.getText().toString();
                                                String Time = timeEditText.getText().toString();
                                                String name = getIntent().getStringExtra("Name");
                                                String phone = getIntent().getStringExtra("number");
                                                String email = getIntent().getStringExtra("email");
                                                String address = getIntent().getStringExtra("address");
                                                String DocUserId = getIntent().getStringExtra("userID");
                                                String UserType = getIntent().getStringExtra("UserType");

                                                Apoint apoint = new Apoint(name, phone, email, address, DocUserId, Date, Time, UserType);
                                                database.getReference().child("Appointments")
                                                        .child(PatientUserId).child(key).setValue(apoint).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                Toast.makeText(BookAppointmentActivity2.this, "128", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(BookAppointmentActivity2.this, "Appoint Book Unsuccessful!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error
                    }
                });
            }
        });
    }
}