package com.fooddelivery.onlinefoodorderingsystem.Models;

public class User {

    private String userId;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(String userId,
                String email,
                String password,
                String role) {

        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // GET USER ID
    public String getUserId() {
        return userId;
    }

    // SET USER ID
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // GET EMAIL
    public String getEmail() {
        return email;
    }

    // SET EMAIL
    public void setEmail(String email) {
        this.email = email;
    }

    // GET PASSWORD
    public String getPassword() {
        return password;
    }

    // SET PASSWORD
    public void setPassword(String password) {
        this.password = password;
    }

    // GET ROLE
    public String getRole() {
        return role;
    }

    // SET ROLE
    public void setRole(String role) {
        this.role = role;
    }
}