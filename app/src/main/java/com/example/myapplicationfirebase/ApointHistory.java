package com.example.myapplicationfirebase;

public class ApointHistory {
    String DocName;
    String DocNumber;
    String DocEmail;
    String DocUserId;
    String date;
    String time;

    public ApointHistory() {
    }

    public ApointHistory(String docName, String docNumber, String docEmail, String docUserId, String date, String time) {
        DocName = docName;
        DocNumber = docNumber;
        DocEmail = docEmail;
        DocUserId = docUserId;
        this.date = date;
        this.time = time;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getDocNumber() {
        return DocNumber;
    }

    public void setDocNumber(String docNumber) {
        DocNumber = docNumber;
    }

    public String getDocEmail() {
        return DocEmail;
    }

    public void setDocEmail(String docEmail) {
        DocEmail = docEmail;
    }

    public String getDocUserId() {
        return DocUserId;
    }

    public void setDocUserId(String docUserId) {
        DocUserId = docUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
