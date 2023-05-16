package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardAmoxicillinActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_amoxicillin);

        editText = findViewById(R.id.editAmoxicillinAmount);
        button = findViewById(R.id.buttonAmoxicillinPlaceOrder);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Medicine Order");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });

    }

    private void placeOrder() {

        String amount = editText.getText().toString().trim();

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference userRef = databaseReference.child(userId);

            userRef.child("Amoxicillin").setValue(amount);
            startActivity(new Intent(CardAmoxicillinActivity.this,OrderPlacedActivity.class));
            finish();
        }

    }
}