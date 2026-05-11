package com.fooddelivery.onlinefoodorderingsystem.Services;


import com.fooddelivery.onlinefoodorderingsystem.Models.DeliveryPerson;import com.fooddelivery.onlinefoodorderingsystem.Models.User;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryService {

    private final String FILE_NAME = "src/main/resources/DB/deliveryguys.txt";

    @Autowired
    private UserService userService;

    // Helper to read assignment status from file
    private Map<String, Boolean> getAssignmentStatus() {
        Map<String, Boolean> statusMap = new HashMap<>();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                return statusMap;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 2) {
                    statusMap.put(data[0].trim(), Boolean.parseBoolean(data[1].trim()));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusMap;
    }

    // Helper to save assignment status to file
    private void saveAssignmentStatus(Map<String, Boolean> statusMap) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Map.Entry<String, Boolean> entry : statusMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL DELIVERY GUYS (Merged from UserService and assignment file)
    public List<DeliveryPerson> getAllDeliveryGuys() {
        List<User> allUsers = userService.getAllUsers();
        Map<String, Boolean> statusMap = getAssignmentStatus();
        List<DeliveryPerson> deliveryList = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getRole().equalsIgnoreCase("DELIVERY")) {
                boolean assigned = statusMap.getOrDefault(user.getUserId(), false);
                DeliveryPerson delivery = new DeliveryPerson(
                        user.getUserId(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole(),
                        assigned
                );
                deliveryList.add(delivery);
            }
        }
        return deliveryList;
    }

    // GET ONE
    public DeliveryPerson getDeliveryGuyById(String userId) {
        List<DeliveryPerson> deliveryList = getAllDeliveryGuys();
        for (DeliveryPerson delivery : deliveryList) {
            if (delivery.getUserId().equals(userId)) {
                return delivery;
            }
        }
        return null;
    }

    // ADD DELIVERY GUY (Actually just ensures status is initialized)
    public DeliveryPerson addDeliveryGuy(DeliveryPerson delivery) {
        Map<String, Boolean> statusMap = getAssignmentStatus();
        statusMap.put(delivery.getUserId(), delivery.isAssigned());
        saveAssignmentStatus(statusMap);
        return delivery;
    }

    // UPDATE
    public DeliveryPerson updateDeliveryGuy(String userId, DeliveryPerson updatedDelivery) {
        Map<String, Boolean> statusMap = getAssignmentStatus();
        statusMap.put(userId, updatedDelivery.isAssigned());
        saveAssignmentStatus(statusMap);
        return updatedDelivery;
    }

    // DELETE
    public String deleteDeliveryGuy(String userId) {
        Map<String, Boolean> statusMap = getAssignmentStatus();
        statusMap.remove(userId);
        saveAssignmentStatus(statusMap);
        return "Delivery Guy Status Removed";
    }

    // ASSIGN DELIVERY GUY
    public DeliveryPerson assignDeliveryGuy() {
        List<DeliveryPerson> deliveryList = getAllDeliveryGuys();
        DeliveryPerson assignedGuy = null;

        for (DeliveryPerson delivery : deliveryList) {
            if (!delivery.isAssigned()) {
                delivery.setAssigned(true);
                assignedGuy = delivery;
                updateDeliveryGuy(delivery.getUserId(), delivery);
                break;
            }
        }
        return assignedGuy;
    }

    // COMPLETE DELIVERY
    public DeliveryPerson completeDelivery(String userId) {
        DeliveryPerson delivery = getDeliveryGuyById(userId);
        if (delivery != null) {
            delivery.setAssigned(false);
            updateDeliveryGuy(userId, delivery);
        }
        return delivery;
    }
}
