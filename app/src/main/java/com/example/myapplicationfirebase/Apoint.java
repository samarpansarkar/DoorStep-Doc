package com.example.myapplicationfirebase;

public class Apoint {

   String name, phone, email, address, DocUserId,Date, Time, UserType;

    public Apoint() {
    }

    public Apoint(String name, String phone, String email, String address, String docUserId,String Date,String Time, String UserType) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        DocUserId = docUserId;
        this.Date = Date;
        this.Time = Time;
        this.UserType = UserType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocUserId() {
        return DocUserId;
    }

    public void setDocUserId(String docUserId) {
        DocUserId = docUserId;
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
