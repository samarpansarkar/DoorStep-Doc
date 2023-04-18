package com.example.myapplicationfirebase;

public class Doctor {
    private String name;
    private String phoneNumber;
    private String specialization;
    private String userId;

    public Doctor() {}

    public Doctor(String name, String phoneNumber, String specialization, String userId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
