package com.example.myapplicationfirebase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nameHisTextView, numberHisTextView, emailHisTextView, useridHisTextView, dateHisTextView, timeHisTextView;

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
