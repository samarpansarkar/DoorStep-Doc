package com.example.myapplicationfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointHistoryAdaptor extends RecyclerView.Adapter<AppointHistoryAdaptor.ViewHolder> {

    private PatientAppointHistoryActivity context;
    private List<AppointHistory> appointHistoryList;

    public AppointHistoryAdaptor(PatientAppointHistoryActivity context, List<AppointHistory> history){
        this.context = context;
        this.appointHistoryList = history;
    }
    @NonNull
    @Override
    public AppointHistoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appoint_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointHistoryAdaptor.ViewHolder holder, int position) {
        AppointHistory history = appointHistoryList.get(position);
        holder.nameHisTextView.setText(history.getPatientName());
        holder.numberHisTextView.setText(history.getPatientNumber());
        holder.emailHisTextView.setText(history.getPatientEmail());
        holder.useridHisTextView.setText(history.getPatentUserId());
        holder.dateHisTextView.setText(history.getDate());
        holder.timeHisTextView.setText(history.getTime());


    }

    @Override
    public int getItemCount() {
        return appointHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameHisTextView, numberHisTextView,emailHisTextView,useridHisTextView,dateHisTextView,timeHisTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameHisTextView = itemView.findViewById(R.id.nameHisTextView);
            numberHisTextView = itemView.findViewById(R.id.numberHisTextView);
            emailHisTextView = itemView.findViewById(R.id.emailHisTextView);
            useridHisTextView = itemView.findViewById(R.id.useridHisTextView);
            dateHisTextView = itemView.findViewById(R.id.dateHisTextView);
            timeHisTextView = itemView.findViewById(R.id.timeHisTextView);
        }
    }
}
