package com.fooddelivery.onlinefoodorderingsystem.Controllers;


import com.fooddelivery.onlinefoodorderingsystem.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/stats/{restaurantId}")
    public Map<String, Object> getStats(@PathVariable String restaurantId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEarnings", restaurantService.getTotalEarnings(restaurantId));
        stats.put("pendingOrders", restaurantService.getPendingOrdersCount(restaurantId));
        return stats;
    }
}
