package com.fooddelivery.onlinefoodorderingsystem.Services;


import com.fooddelivery.onlinefoodorderingsystem.Models.CartItem;import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final String FILE_NAME = "src/main/resources/DB/carts.txt";

    // GET ALL CART ITEMS FOR A USER
    public List<CartItem> getCart(String userId) {
        List<CartItem> allItems = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return allItems;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length < 4) continue;
                
                if (data[0].trim().equals(userId)) {
                    allItems.add(new CartItem(
                        data[0].trim(),
                        data[1].trim(),
                        Double.parseDouble(data[2].trim()),
                        data[3].trim()
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allItems;
    }

    // ADD ITEM TO CART
    public void addToCart(CartItem item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(String.format("%s,%s,%.2f,%s", 
                item.getUserId(), 
                item.getFoodName(), 
                item.getPrice(), 
                item.getRestaurantId()));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // REMOVE SPECIFIC ITEM FROM CART
    public void removeFromCart(String userId, String foodName) {
        List<CartItem> allItems = getAllItems();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            boolean removed = false;
            for (CartItem item : allItems) {
                // Remove only one instance of the item for this user
                if (!removed && item.getUserId().equals(userId) && item.getFoodName().equals(foodName)) {
                    removed = true;
                    continue;
                }
                bw.write(String.format("%s,%s,%.2f,%s", 
                    item.getUserId(), 
                    item.getFoodName(), 
                    item.getPrice(), 
                    item.getRestaurantId()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CLEAR CART FOR USER
    public void clearCart(String userId) {
        List<CartItem> allItems = getAllItems();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (CartItem item : allItems) {
                if (!item.getUserId().equals(userId)) {
                    bw.write(String.format("%s,%s,%.2f,%s", 
                        item.getUserId(), 
                        item.getFoodName(), 
                        item.getPrice(), 
                        item.getRestaurantId()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HELPER: GET ALL ITEMS FROM FILE
    private List<CartItem> getAllItems() {
        List<CartItem> allItems = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return allItems;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length < 4) continue;
                allItems.add(new CartItem(
                    data[0].trim(),
                    data[1].trim(),
                    Double.parseDouble(data[2].trim()),
                    data[3].trim()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allItems;
    }
}
