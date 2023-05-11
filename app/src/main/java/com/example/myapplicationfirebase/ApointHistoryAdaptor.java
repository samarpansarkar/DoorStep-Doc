package com.example.myapplicationfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApointHistoryAdaptor extends RecyclerView.Adapter<ApointHistoryAdaptor.ViewHolder>{

    private DocAppointHistoryActivity context;
    private List<ApointHistory> apointHistoryList;

    public ApointHistoryAdaptor(DocAppointHistoryActivity context, List<ApointHistory> apHistory){
        this.context = context;
        this.apointHistoryList = apHistory;
    }

    @NonNull
    @Override
    public ApointHistoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apoint_history, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApointHistoryAdaptor.ViewHolder holder, int position) {
        ApointHistory apointHistory = apointHistoryList.get(position);
        holder.nameHisTextView.setText(apointHistory.getDocName());
        holder.numberHisTextView.setText(apointHistory.getDocNumber());
        holder.emailHisTextView.setText(apointHistory.getDocEmail());
        holder.useridHisTextView.setText(apointHistory.getDocUserId());
        holder.dateHisTextView.setText(apointHistory.getDate());
        holder.timeHisTextView.setText(apointHistory.getTime());
    }

    @Override
    public int getItemCount() {
        return apointHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameHisTextView, numberHisTextView,emailHisTextView,useridHisTextView,dateHisTextView,timeHisTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameHisTextView = itemView.findViewById(R.id.nameHistTextView);
            numberHisTextView = itemView.findViewById(R.id.numberHistTextView);
            emailHisTextView = itemView.findViewById(R.id.emailHistTextView);
            useridHisTextView = itemView.findViewById(R.id.useridHistTextView);
            dateHisTextView = itemView.findViewById(R.id.dateHistTextView);
            timeHisTextView = itemView.findViewById(R.id.timeHistTextView);
        }
    }
}

