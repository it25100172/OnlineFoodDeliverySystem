package com.fooddelivery.onlinefoodorderingsystem.Controllers;

import com.fooddelivery.onlinefoodorderingsystem.Models.DeliveryPerson;
import com.fooddelivery.onlinefoodorderingsystem.Services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PutMapping("/delivery-complete/{userId}")
    public DeliveryPerson completeDelivery(
            @PathVariable String userId){

        return deliveryService
                .completeDelivery(userId);
    }

    @GetMapping("/assign-delivery")
    public DeliveryPerson assignDeliveryGuy(){

        return deliveryService.assignDeliveryGuy();
    }

    // GET ALL
    @GetMapping("/delivery-guys")
    public Object getAllDeliveryGuys(){

        return deliveryService
                .getAllDeliveryGuys();
    }

    // GET ONE
    @GetMapping("/delivery-guys/{userId}")
    public DeliveryPerson getDeliveryGuy(
            @PathVariable String userId){

        return deliveryService
                .getDeliveryGuyById(userId);
    }

    // ADD
    @PostMapping("/delivery-guys")
    public DeliveryPerson addDeliveryGuy(
            @RequestBody DeliveryPerson delivery){

        return deliveryService
                .addDeliveryGuy(delivery);
    }

    // UPDATE
    @PutMapping("/delivery-guys/{userId}")
    public DeliveryPerson updateDeliveryGuy(
            @PathVariable String userId,

            @RequestBody DeliveryPerson delivery){

        return deliveryService
                .updateDeliveryGuy(userId,
                        delivery);
    }

    // DELETE
    @DeleteMapping("/delivery-guys/{userId}")
    public String deleteDeliveryGuy(
            @PathVariable String userId){

        return deliveryService.deleteDeliveryGuy(userId);
    }
}