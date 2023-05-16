package com.example.myapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderMedicineActivity extends AppCompatActivity {
    private CardView CardParacetamol,CardAmoxicillin,CardTyleno,CardPrednisone,CardIbuprofen,CardAdvil;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_medicine);

        databaseReference = FirebaseDatabase.getInstance().getReference("Medicine Data");

        CardParacetamol = findViewById(R.id.CardParacetamol);
        CardAmoxicillin = findViewById(R.id.CardAmoxicillin);
        CardTyleno = findViewById(R.id.CardTylenol);
        CardPrednisone = findViewById(R.id.CardPrednisone);
        CardIbuprofen = findViewById(R.id.CardIbuprofen);
        CardAdvil = findViewById(R.id.CardAdvil);


        CardParacetamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderMedicineActivity.this, CardParacetamolActivity.class));
            }
        });

        CardAmoxicillin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderMedicineActivity.this, CardAmoxicillinActivity.class));
            }
        });

        CardTyleno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderMedicineActivity.this, CardTylenoActivity.class));
            }
        });

    }
}