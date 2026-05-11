package com.fooddelivery.onlinefoodorderingsystem.Models;

public class Food {

    private String foodId;
    private String foodName;
    private double price;
    private String restaurantId;

    public Food() {
    }

    public Food(String foodId,
                String foodName,
                double price,
                String restaurantId) {

        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}