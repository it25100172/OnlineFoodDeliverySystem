package com.fooddelivery.onlinefoodorderingsystem.Models;

public class Order {

    private String orderId;
    private String customerId;
    private String foodName;
    private double total;
    private String deliveryId;
    private String status;
    private String restaurantId;

    public Order() {
    }

    public Order(
            String orderId,
            String customerId,
            String foodName,
            double total,
            String deliveryId,
            String status,
            String restaurantId
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.foodName = foodName;
        this.total = total;
        this.deliveryId = deliveryId;
        this.status = status;
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}