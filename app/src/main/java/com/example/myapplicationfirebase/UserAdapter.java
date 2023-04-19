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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.DoctorViewHolder> {
    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {

        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_layout, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        User user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.phoneNumberTextView.setText(user.getPhoneNumber());
        holder.specializationTextView.setText(user.getSpecialization());

        //chat ----------------------
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("Name", user.getName());
                intent.putExtra("userID", user.getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return users.size();
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



