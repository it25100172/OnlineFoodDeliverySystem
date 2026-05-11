package com.fooddelivery.onlinefoodorderingsystem.Services;

import com.fooddelivery.onlinefoodorderingsystem.Models.Order;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private OrderService orderService;

    // GET TOTAL EARNINGS
    public double getTotalEarnings(String restaurantId) {
        List<Order> orders = orderService.getAllOrders();
        double total = 0;
        for (Order order : orders) {
            if (restaurantId.equals(order.getRestaurantId()) && 
                "DELIVERED".equalsIgnoreCase(order.getStatus())) {
                total += order.getTotal();
            }
        }
        return total;
    }

    // GET TOTAL PENDING ORDERS
    public int getPendingOrdersCount(String restaurantId) {
        List<Order> orders = orderService.getAllOrders();
        int count = 0;
        for (Order order : orders) {
            if (restaurantId.equals(order.getRestaurantId()) && 
                "PENDING".equalsIgnoreCase(order.getStatus())) {
                count++;
            }
        }
        return count;
    }
}
