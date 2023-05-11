package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientMainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button doc_booking_button;
    private CardView CardAppoitmentBooking,CardFindDoctor,CardExit,CardDocAppointHistory,CardPatientAppointHistory;
    private ImageView logOut;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);


        CardFindDoctor = findViewById(R.id.CardFindDoctor);
        CardExit = findViewById(R.id.CardExit);
        CardAppoitmentBooking = findViewById(R.id.CardAppoitmentBooking);
        CardDocAppointHistory = findViewById(R.id.CardDocAppointHistory);
        CardPatientAppointHistory = findViewById(R.id.CardPatientAppointHistory);


       //Toolbar
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        CardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(PatientMainActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PatientMainActivity.this, StartActivity.class));
                finish();
            }
        });

        CardAppoitmentBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActivity.this, BookAppointmentActivity.class));
            }
        });

        CardFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActivity.this,DoctorSearchActivity.class));
            }
        });

        CardDocAppointHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientMainActivity.this,PatientAppointHistoryActivity.class));

            }
        });

        CardPatientAppointHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(PatientMainActivity.this,DocAppointHistoryActivity.class)));
            }
        });
    }

    //toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return  true;
    }

}
