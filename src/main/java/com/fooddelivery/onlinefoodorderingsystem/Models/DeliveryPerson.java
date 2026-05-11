package com.fooddelivery.onlinefoodorderingsystem.Models;

public class DeliveryPerson extends User {

    private boolean assigned;

    public DeliveryPerson() {
    }

    public DeliveryPerson(String userId,
                          String email,
                          String password,
                          String role,
                          boolean assigned) {

        super(userId, email, password, role);

        this.assigned = assigned;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}