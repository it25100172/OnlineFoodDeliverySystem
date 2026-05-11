package com.fooddelivery.onlinefoodorderingsystem.Controllers;

import com.fooddelivery.onlinefoodorderingsystem.Models.User;
import com.fooddelivery.onlinefoodorderingsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login.html";
    }


    @PostMapping("/users")
    @ResponseBody
    public User addUser(@RequestBody User user){

        User savedUser =
                userService.addUser(user);

        if(savedUser == null){

            return null;
        }

        return savedUser;
    }


    @GetMapping("/register")
    public String showRegisterPage(){

        return "redirect:/register.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        User user = userService.login(email, password);
        System.out.println(user);

        if(user == null) {

            return "redirect:/login.html?error=true";
        }

        if(user.getRole().equalsIgnoreCase("CUSTOMER")) {
            return "redirect:/customer.html?userId="
                    + user.getUserId();
        }

        if(user.getRole().equalsIgnoreCase("DELIVERY")) {

            return "redirect:/delivery.html?userId="
                    + user.getUserId();
        }

        if(user.getRole().equalsIgnoreCase("RESTAURANT")) {
            return "redirect:/restaurant.html?userId="
                    + user.getUserId();
        }

        return "redirect:/login.html?error=true";
    }
}