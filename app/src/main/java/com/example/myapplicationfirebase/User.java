package com.example.myapplicationfirebase;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;
    private String userId;
    private String usertype;
    public User() {}

    public User(String name, String email, String phoneNumber, String specialization, String userId, String usertype) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.userId = userId;
        this.usertype = usertype;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
