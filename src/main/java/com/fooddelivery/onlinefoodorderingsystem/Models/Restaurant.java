package com.fooddelivery.onlinefoodorderingsystem.Models;

public class Restaurant extends User {

    private double totEarns;

    public Restaurant() {
    }

    public Restaurant(String userId, String email, String password, double totEarns) {
        super(userId, email, password, "RESTAURANT");
        this.totEarns = totEarns;
    }

    public double getTotEarns() {
        return totEarns;
    }

    public void setTotEarns(double totEarns) {
        this.totEarns = totEarns;
    }
}