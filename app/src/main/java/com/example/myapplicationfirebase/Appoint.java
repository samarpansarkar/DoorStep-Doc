package com.example.myapplicationfirebase;

public class Appoint {
    String PatientName;
    String PatientNumber;
    String PatientEmail;
    String PatientUserId;

    String Date;
    String Time;
    String UserType;

    public Appoint(String name, String phone, String email, String address, String docUserId) {
    }

    public Appoint(String patientName, String patientNumber, String patientEmail, String patientUserId, String date, String time, String UserType) {
        this.PatientName = patientName;
        this.PatientNumber = patientNumber;
        this.PatientEmail = patientEmail;
        this.PatientUserId = patientUserId;
        this.Date = date;
        this.Time = time;
        this.UserType = UserType;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientNumber() {
        return PatientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        PatientNumber = patientNumber;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public String getPatientUserId() {
        return PatientUserId;
    }

    public void setPatientUserId(String patientUserId) {
        PatientUserId = patientUserId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
