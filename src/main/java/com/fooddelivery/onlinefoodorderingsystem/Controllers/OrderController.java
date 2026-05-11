package com.fooddelivery.onlinefoodorderingsystem.Controllers;


import com.fooddelivery.onlinefoodorderingsystem.Models.Order;
import com.fooddelivery.onlinefoodorderingsystem.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET ALL ORDERS
    @GetMapping("/orders")
    public Object getAllOrders(){

        return orderService.getAllOrders();
    }


    // ADD ORDER
    @PostMapping("/orders")
    @ResponseBody
    public Order addOrder(
            @RequestBody Order order){

        return orderService.addOrder(order);
    }

    // UPDATE STATUS
    @PutMapping("/orders/{orderId}/{status}")
    @ResponseBody
    public Order updateOrderStatus(

            @PathVariable String orderId,

            @PathVariable String status){

        return orderService
                .updateOrderStatus(
                        orderId,
                        status
                );
    }

}