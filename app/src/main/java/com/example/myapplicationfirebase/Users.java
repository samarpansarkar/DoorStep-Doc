package com.example.myapplicationfirebase;

public class Users {
    String userName, phone, specialization;

    public Users() {
    }

    public Users(String userId, String userName, String maill, String password, String profilepic, String status) {

        this.userName = userName;
        this.phone = phone;
        this.specialization = specialization;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
};



