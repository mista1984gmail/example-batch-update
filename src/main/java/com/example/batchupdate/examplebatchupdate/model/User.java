package com.example.batchupdate.examplebatchupdate.model;

public class User {
    private int user_id;
    private String user_name;
    private String address;
    private String email;
    private String telephone;


    public User(String user_name, String address, String email, String telephone) {
        this.user_name = user_name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
