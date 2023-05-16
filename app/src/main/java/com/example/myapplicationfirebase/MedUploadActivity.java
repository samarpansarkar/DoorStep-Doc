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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MedUploadActivity extends AppCompatActivity {

    private EditText medName, medDescription, medMFD, medEXP, medPrice;
    private Button confirm_button;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_upload);

        medName = findViewById(R.id.med_register_MedicineName);
        medDescription = findViewById(R.id.med_register_MedicineDescription);
        medMFD = findViewById(R.id.register_MedicineManufactureDate);
        medEXP = findViewById(R.id.Med_register_MedicineExpiryDate);
        medPrice = findViewById(R.id.Med_register_MedicinePrice);
        confirm_button = findViewById(R.id.med_register_button);


        String MedSellerId = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference MedRef = FirebaseDatabase.getInstance().getReference("MedSeller").child(MedSellerId);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            String txt_medName = medName.getText().toString().trim();
                            String txt_medDescrioption = medDescription.getText().toString().trim();
                            String txt_medMFD = medMFD.getText().toString().trim();
                            String txt_medEXP = medEXP.getText().toString().trim();
                            String txt_medPrice = medPrice.getText().toString().trim();
                            String key = MedRef.child("Medicine Data").push().getKey();

                            if (TextUtils.isEmpty(txt_medName)) {
                                Toast.makeText(MedUploadActivity.this, "Please enter medicine name!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(txt_medDescrioption)) {
                                Toast.makeText(MedUploadActivity.this, "Please enter medicine description!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(txt_medMFD)) {
                                Toast.makeText(MedUploadActivity.this, "Please enter MFD!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(txt_medEXP)) {
                                Toast.makeText(MedUploadActivity.this, "Please enter EXP!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(txt_medPrice)) {
                                Toast.makeText(MedUploadActivity.this, "Please enter medicine price!!", Toast.LENGTH_SHORT).show();
                            } else {
                            }
                            MedicineData medicineData = new MedicineData(txt_medName, txt_medDescrioption, txt_medMFD, txt_medEXP, txt_medPrice);

                            String MedSellerId = firebaseAuth.getCurrentUser().getUid();
                            database.getReference().child("Medicine Data")
                                    .child(MedSellerId).child(key).setValue(medicineData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(MedUploadActivity.this, "Medicine data save!!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MedUploadActivity.this, MedicineMainActivity.class);
                                            finish();
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MedUploadActivity.this, "Unsuccessful!!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}