package com.example.myapplicationfirebase;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class UserAdpter extends RecyclerView.Adapter<UserAdpter.viewholder> {
    DoctorSearchActivity doctorSearchActivity;
         ArrayList<Users> usersArrayList;
    public UserAdpter(DoctorSearchActivity doctorSearchActivity, ArrayList<Users> usersArrayList) {
        this.doctorSearchActivity = doctorSearchActivity;
        this.usersArrayList= usersArrayList;
    }

    @NonNull
    @Override
    public UserAdpter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(doctorSearchActivity).inflate(R.layout.item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdpter.viewholder holder, int position) {

        Users users = usersArrayList.get(position);
        holder.username.setText(users.userName);
        holder.phone.setText(users.phone);
        holder.specialization.setText(users.specialization);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        public TextView phone;
        TextView username;
        TextView specialization;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.card_username);
            phone= itemView.findViewById(R.id.card_phone);
            specialization= itemView.findViewById(R.id.card_specialization);

        }
    }
}
