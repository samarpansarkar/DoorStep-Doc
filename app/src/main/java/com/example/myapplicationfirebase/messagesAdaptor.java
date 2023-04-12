package com.example.myapplicationfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class messagesAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Messages> messagesArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    public messagesAdaptor(Context context, ArrayList<Messages> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout_item, parent, false);
            return new senderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout_item, parent, false);
            return new reciverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Messages messages = messagesArrayList.get(position);
        if (holder.getClass() == senderViewHolder.class) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.txtmessage.setText(messages.getMessage());
        } else {
            reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.txtmessage.setText(messages.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Messages messages = messagesArrayList.get(position);
        String currentUserName = messages.getSenderName();
        if (messages.getSenderName().equals(currentUserName)) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    static class senderViewHolder extends RecyclerView.ViewHolder {
        TextView txtmessage;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmessage = itemView.findViewById(R.id.senderMsg);
        }
    }

    static class reciverViewHolder extends RecyclerView.ViewHolder {
        TextView txtmessage;

        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmessage = itemView.findViewById(R.id.reciverMsg);
        }
    }
}

