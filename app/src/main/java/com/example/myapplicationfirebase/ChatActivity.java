package com.example.myapplicationfirebase;

import static java.lang.System.currentTimeMillis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
        private RecyclerView recyclerView;
        private EditText etMessage;
        private TextView chat_user_name;
        private CardView sentBtn;
        private ArrayList<Messages> messageArrayList;
        private messagesAdaptor MessageAdapter;
        private FirebaseAuth firebaseAuth;
        private FirebaseDatabase database;
        private String senderRoom, reciverRoom, receiverUid, reciverName, senderUid;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);

            database = FirebaseDatabase.getInstance();
            firebaseAuth = FirebaseAuth.getInstance();

            // Get Sender and Receiver UIDs
            receiverUid = getIntent().getStringExtra("userID");
            reciverName = getIntent().getStringExtra("Name");
            senderUid = firebaseAuth.getUid();

            //Initialize the room's
            senderRoom = senderUid +"_"+ receiverUid;
            reciverRoom = receiverUid +"_"+ senderUid;

            // Initialize views
            recyclerView = findViewById(R.id.messageRecyclerview);
            etMessage = findViewById(R.id.editTextMsg);
            sentBtn = findViewById(R.id.sendMsgButton);
            chat_user_name = findViewById(R.id.chat_user_name);

            messageArrayList = new ArrayList<>();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            MessageAdapter = new messagesAdaptor(ChatActivity.this,messageArrayList);
            recyclerView.setAdapter(MessageAdapter);

            //Initialize reciverName
            chat_user_name.setText(""+reciverName);

            DatabaseReference reference = database.getReference().child("Users").child(firebaseAuth.getUid());
            DatabaseReference ChatReference = database.getReference().child("Chats").child(senderRoom).child("messages");
            ChatReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    messageArrayList.clear();
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        Messages messages = dataSnapshot.getValue(Messages.class);
                        messageArrayList.add(messages);

                    }
                    MessageAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ChatActivity.this, "Failed to retrieve messages: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            sentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = etMessage.getText().toString();
                    if (message.isEmpty())
                    {
                        Toast.makeText(ChatActivity.this, "Please enter Valid Message!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    etMessage.setText("");
                    Date date = new Date();
                    Messages messages = new Messages(message, senderUid, date.getTime());

                    database.getReference().child("Chats")
                            .child(senderRoom)
                            .child("messages")
                            .push()
                            .setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    database.getReference().child("Chats")
                                            .child(reciverRoom)
                                            .child("messages")
                                            .push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            });
                }
            });
        }
}