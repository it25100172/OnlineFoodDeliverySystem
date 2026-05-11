package com.fooddelivery.onlinefoodorderingsystem.Controllers;


import com.fooddelivery.onlinefoodorderingsystem.Models.CartItem;
import com.fooddelivery.onlinefoodorderingsystem.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody CartItem item) {
        cartService.addToCart(item);
    }

    @DeleteMapping("/remove/{userId}/{foodName}")
    public void removeFromCart(@PathVariable String userId, @PathVariable String foodName) {
        cartService.removeFromCart(userId, foodName);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}
