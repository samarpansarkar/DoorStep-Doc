package com.example.myapplicationfirebase;

public class Messages {
    String message;
    String senderName;
    long timeStamp;

    public Messages() {
    }

    public Messages(String message, String senderName, long timeStamp) {
        this.message = message;
        this.senderName = senderName;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
