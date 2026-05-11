package com.fooddelivery.onlinefoodorderingsystem.Models;

public class Beverage extends Food {

    public Beverage() {
    }

    public Beverage(String foodId, String name, double price, String restaurantId) {
        super(foodId, name, price, restaurantId);
    }
}