package com.example.myapplicationfirebase.drSearch;

public class Users {
    String name , phonenumber, specialization;

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Users(String name, String phonenumber, String specialization) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.specialization = specialization;


    }
}
