package com.example.myapplicationfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private Context context;
    private List<Doctor> doctors;

    public DoctorAdapter(Context context, List<Doctor> doctors) {

        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_layout, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.phoneNumberTextView.setText(doctor.getPhoneNumber());
        holder.specializationTextView.setText(doctor.getSpecialization());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("doctorName", doctor.getName());
                context.startActivity(intent);
            }
        });


        //chat ----------------------
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("doctorName", doctor.getName());
                intent.putExtra("userID",doctor.getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return doctors.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView phoneNumberTextView;
        public TextView specializationTextView;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
        }
    }
}



