package com.example.myapplicationfirebase;

public class Doctor {
    private String name;
    private String phoneNumber;
    private String specialization;

    public Doctor() {}

    public Doctor(String name, String phoneNumber, String specialization) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
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
}
