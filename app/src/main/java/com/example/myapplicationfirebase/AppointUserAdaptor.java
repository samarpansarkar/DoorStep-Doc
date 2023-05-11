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

public class AppointUserAdaptor extends RecyclerView.Adapter<AppointUserAdaptor.ViewHolder>{

    private Context context;
    private List<AppointUser> appointUserList;

    public AppointUserAdaptor(Context context, List<AppointUser> users) {

        this.context = context;
        this.appointUserList = users;
    }
    @NonNull
    @Override
    public AppointUserAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appoint_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointUserAdaptor.ViewHolder holder, int position) {
        AppointUser user = appointUserList.get(position);
        holder.nameTextView.setText(user.getName());
        holder.phoneNumberTextView.setText(user.getPhoneNumber());
        holder.specializationTextView.setText(user.getSpecialization());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookAppointmentActivity2.class);
                intent.putExtra("Name", user.getName());
                intent.putExtra("userID", user.getUserId());
                intent.putExtra("number",user.getPhoneNumber());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("address",user.getAddress());
                intent.putExtra("UserType",user.getUsertype());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView phoneNumberTextView;
        public TextView specializationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.AppointNameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.AppointPhoneNumberTextView);
            specializationTextView = itemView.findViewById(R.id.AppointSpecializationTextView);
        }
    }
}
