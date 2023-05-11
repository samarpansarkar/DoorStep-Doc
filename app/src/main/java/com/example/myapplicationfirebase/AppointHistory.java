package com.example.myapplicationfirebase;

import androidx.annotation.StringDef;

public class AppointHistory {
    String patientName;
    String patientNumber;
    String patientEmail;
    String patentUserId;
    String date;
    String time;

    public AppointHistory() {
    }

    public AppointHistory(String patientName , String patientNumber, String patientEmail,String patientUserId, String date,String time) {
        this.patientName = patientName;
        this.patientNumber = patientNumber;
        this.patientEmail = patientEmail;
        this.patentUserId = patientUserId;
        this.date = date;
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatentUserId() {
        return patentUserId;
    }

    public void setPatentUserId(String patentUserId) {
        this.patentUserId = patentUserId;
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
