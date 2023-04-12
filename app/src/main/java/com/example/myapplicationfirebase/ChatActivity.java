package com.example.myapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    String ReciverName;
    String senderRoom,reciverRoom;
    private TextView chatUserName;
    private CardView sendButton;
    private RecyclerView messageAdapter;

    private ArrayList<Messages> messagesArrayList;

    messagesAdaptor msgAdapter;
    private EditText editTextMsg;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Patient").child("name").child("name");
    private DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chats").child("senderRoom").child("messages");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ReciverName = getIntent().getStringExtra("doctorName");

        chatUserName = findViewById(R.id.chat_user_name);

        chatUserName.setText(""+ReciverName);


        sendButton = findViewById(R.id.sendMsgButton);
        editTextMsg = findViewById(R.id.editTextMsg);
        messagesArrayList = new ArrayList<>();

        messageAdapter = findViewById(R.id.messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageAdapter.setLayoutManager(linearLayoutManager);
        msgAdapter = new messagesAdaptor(ChatActivity.this,messagesArrayList);
        messageAdapter.setAdapter(msgAdapter);




        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                   Messages messages = dataSnapshot.getValue(Messages.class);
                   messagesArrayList.add(messages);

               }
                msgAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMsg.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Please enter valid Message!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editTextMsg.setText("");
                Date date = new Date();

                // Attach a ValueEventListener to the databaseReference
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Get the value from the dataSnapshot
                        String senderName = dataSnapshot.getValue(String.class);

                        senderRoom = senderName + ReciverName;
                        reciverRoom = ReciverName + senderName;

                        // Use the senderName variable as needed
                        Messages messages = new Messages(message, senderName, date.getTime());

                        firebaseDatabase.getReference().child("Chats")
                                .child(senderRoom)
                                .child("messages")
                                .push()
                                .setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        firebaseDatabase.getReference().child("Chats")
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ChatActivity.this, "49-77", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}