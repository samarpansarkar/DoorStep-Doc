package com.example.myapplicationfirebase;

import static com.example.myapplicationfirebase.R.id.userstatus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.userstatus.setText(users.status);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView username;
        TextView userstatus;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            userstatus= itemView.findViewById(R.id.userstatus);

        }
    }
}
