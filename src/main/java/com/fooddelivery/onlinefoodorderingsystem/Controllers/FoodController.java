package com.fooddelivery.onlinefoodorderingsystem.Controllers;


import com.fooddelivery.onlinefoodorderingsystem.Models.Food;import com.fooddelivery.onlinefoodorderingsystem.Services.FoodService;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    // GET ALL FOODS
    @GetMapping("/foods")
    public Object getAllFoods(){

        return foodService.getAllFoods();
    }

    // ADD FOOD
    @PostMapping("/foods")
    public Food addFood(@RequestBody Food food){

        return foodService.addFood(food);
    }

    // DELETE FOOD
    @DeleteMapping("/foods/{foodId}")
    public String deleteFood(@PathVariable String foodId){

        return foodService.deleteFood(foodId);
    }

    // UPDATE FOOD
    @PutMapping("/foods/{foodId}")
    public Food updateFood(@PathVariable String foodId,
                           @RequestBody Food updatedFood){

        return foodService.updateFood(foodId, updatedFood);
    }
}