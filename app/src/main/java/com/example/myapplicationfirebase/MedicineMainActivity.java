package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MedicineMainActivity extends AppCompatActivity {

    private TextView medUserName;
    private CardView CardMedicineUplaad, CardMedOrderDetails, CardMedProfile,CardMedExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_main);

        CardMedicineUplaad = findViewById(R.id.CardMedicineUplaad);
        CardMedOrderDetails = findViewById(R.id.CardMedOrderDetails);
        CardMedProfile = findViewById(R.id.CardmedProfile);
        CardMedExit = findViewById(R.id.CardMedExit);


        CardMedicineUplaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineMainActivity.this,MedUploadActivity.class));

            }
        });

        CardMedExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MedicineMainActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedicineMainActivity.this, StartActivity.class));
                finish();
            }
        });
    }
}