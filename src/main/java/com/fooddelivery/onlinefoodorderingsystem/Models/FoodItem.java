package com.fooddelivery.onlinefoodorderingsystem.Models;

public class FoodItem extends Food {

    public FoodItem() {
    }

    public FoodItem(String foodId,
                    String foodName,
                    double price,
                    String restaurantId) {

        super(foodId, foodName, price, restaurantId);
    }
}