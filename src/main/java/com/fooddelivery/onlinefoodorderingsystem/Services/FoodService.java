package com.fooddelivery.onlinefoodorderingsystem.Services;


import com.fooddelivery.onlinefoodorderingsystem.Models.Food;import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    private final String FILE_NAME =
            "src/main/resources/DB/foods.txt";

    // GET ALL FOODS
    public List<Food> getAllFoods(){

        List<Food> foodList = new ArrayList<>();

        try{

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length < 3) continue;

                Food food = new Food();
                food.setFoodId(data[0].trim());
                food.setFoodName(data[1].trim());
                food.setPrice(Double.parseDouble(data[2].trim()));
                
                if(data.length >= 4){
                    food.setRestaurantId(data[3].trim());
                } else {
                    food.setRestaurantId("R1");
                }

                foodList.add(food);
            }

            br.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return foodList;
    }

    // GENERATE FOOD ID
    public String generateFoodId(){

        int count = getAllFoods().size() + 1;

        return "F" + count;
    }

    // ADD FOOD
    public Food addFood(Food food){

        food.setFoodId(
                generateFoodId()
        );

        try{

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME, true)
                    );

            bw.write(
                    food.getFoodId() + "," +
                            food.getFoodName() + "," +
                            food.getPrice() + "," +
                            food.getRestaurantId()
            );

            bw.newLine();

            bw.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return food;
    }

    // DELETE FOOD
    public String deleteFood(String foodId){

        List<Food> foodList = getAllFoods();

        try{

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for(Food food : foodList){

                if(!food.getFoodId().equals(foodId)){

                    bw.write(
                            food.getFoodId() + "," +
                                    food.getFoodName() + "," +
                                    food.getPrice() + "," +
                                    food.getRestaurantId()
                    );

                    bw.newLine();
                }
            }

            bw.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return "Food Deleted";
    }

    // UPDATE FOOD
    public Food updateFood(String foodId,
                           Food updatedFood){

        List<Food> foodList = getAllFoods();

        try{

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for(Food food : foodList){

                if(food.getFoodId().equals(foodId)){

                    food.setFoodName(
                            updatedFood.getFoodName()
                    );

                    food.setPrice(
                            updatedFood.getPrice()
                    );

                    food.setRestaurantId(
                            updatedFood.getRestaurantId()
                    );
                }

                bw.write(
                        food.getFoodId() + "," +
                                food.getFoodName() + "," +
                                food.getPrice() + "," +
                                food.getRestaurantId()
                );

                bw.newLine();
            }

            bw.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return updatedFood;
    }
}