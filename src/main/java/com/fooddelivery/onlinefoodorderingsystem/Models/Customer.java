package com.fooddelivery.onlinefoodorderingsystem.Models;

public class Customer extends User {

    public Customer() {
    }

    public Customer(String userId,String email, String password) {
        super(userId,email, password, "CUSTOMER");
    }
}