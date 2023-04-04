package com.example.myapplicationfirebase.drSearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfirebase.DoctorSearchActivity;
import com.example.myapplicationfirebase.R;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.viewholder> {
    DoctorSearchActivity doctorSearchActivity;
    ArrayList<Users> usersArrayList;
    public userAdapter(DoctorSearchActivity doctorSearchActivity, ArrayList<Users> usersArrayList) {
        this.doctorSearchActivity = doctorSearchActivity;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public userAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(doctorSearchActivity).inflate(R.layout.item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapter.viewholder holder, int position) {

        Users users = usersArrayList.get(position);
        holder.cardname.setText(users.name);
        holder.cardphone.setText(users.phonenumber);
        holder.cardspecialization.setText(users.specialization);

    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView cardname,cardphone,cardspecialization;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            cardname = itemView.findViewById(R.id.cardname);
            cardphone = itemView.findViewById(R.id.cardphone);
            cardspecialization = itemView.findViewById(R.id.cardspecialization);
        }
    }
}
