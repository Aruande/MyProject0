package com.revature.models;

public class User {
    private int user_id;
    private String username;
    private String email;
    private String address;

    //no args
    public User() {
    }
    //All args
    public User(int user_id, String username, String email, String address) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.address = address;
    }
    // all args minus id
    public User(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
