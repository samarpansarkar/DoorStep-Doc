package com.example.myapplicationfirebase;

public class Messages {
    String message;
    String senderId;
    long timeStamp;

    public Messages() {
    }

    public Messages(String message, String senderId, long timeStamp) {
        this.message = message;
        this.senderId = senderId;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderId;
    }

    public void setSenderName(String senderName) {
        this.senderId = senderName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
