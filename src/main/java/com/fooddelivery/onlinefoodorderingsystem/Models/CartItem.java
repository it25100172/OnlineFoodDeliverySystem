package com.fooddelivery.onlinefoodorderingsystem.Models;

public class CartItem {
    private String userId;
    private String foodName;
    private double price;
    private String restaurantId;

    public CartItem() {
    }

    public CartItem(String userId, String foodName, double price, String restaurantId) {
        this.userId = userId;
        this.foodName = foodName;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
