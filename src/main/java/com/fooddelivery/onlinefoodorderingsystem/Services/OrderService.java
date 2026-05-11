package com.fooddelivery.onlinefoodorderingsystem.Services;

import com.fooddelivery.onlinefoodorderingsystem.Models.Order;import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final String FILE_NAME =
            "src/main/resources/DB/orders.txt";

    // GET ALL ORDERS
    public List<Order> getAllOrders(){

        List<Order> orderList =
                new ArrayList<>();

        try{

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while((line = br.readLine()) != null){

                // SKIP EMPTY LINES
                if(line.trim().isEmpty()){
                    continue;
                }

                System.out.println(line);

                String[] data = line.split(",");

                // SAFE CHECK
                if(data.length < 6){
                    continue;
                }

                Order order = new Order();

                order.setOrderId(
                        data[0].trim()
                );

                order.setCustomerId(
                        data[1].trim()
                );

                order.setFoodName(
                        data[2].trim()
                );

                order.setTotal(
                        Double.parseDouble(
                                data[3].trim()
                        )
                );

                order.setDeliveryId(
                        data[4].trim()
                );

                order.setStatus(
                        data[5].trim()
                );

                if(data.length >= 7){
                    order.setRestaurantId(data[6].trim());
                } else {
                    order.setRestaurantId("R1");
                }

                orderList.add(order);
            }

            br.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return orderList;
    }

    // GENERATE ORDER ID
    public String generateOrderId(){

        int count =
                getAllOrders().size() + 1;

        return "ORD" + count;
    }

    // ADD ORDER
    public Order addOrder(Order order){

        order.setOrderId(
                generateOrderId()
        );

        try{

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    FILE_NAME,
                                    true
                            )
                    );

            bw.write(
                    order.getOrderId() + "," +
                            order.getCustomerId() + "," +
                            order.getFoodName() + "," +
                            order.getTotal() + "," +
                            order.getDeliveryId() + "," +
                            order.getStatus() + "," +
                            order.getRestaurantId()
            );

            bw.newLine();

            bw.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return order;
    }

    // UPDATE STATUS
    public Order updateOrderStatus(
            String orderId,
            String status){

        List<Order> orderList =
                getAllOrders();

        Order updatedOrder = null;

        try{

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for(Order order : orderList){

                if(order.getOrderId()
                        .equals(orderId)){

                    order.setStatus(status);

                    updatedOrder = order;
                }

                bw.write(
                        order.getOrderId() + "," +
                                order.getCustomerId() + "," +
                                order.getFoodName() + "," +
                                order.getTotal() + "," +
                                order.getDeliveryId() + "," +
                                order.getStatus() + "," +
                                order.getRestaurantId()
                );

                bw.newLine();
            }

            bw.close();

        }catch(Exception e){

            e.printStackTrace();
        }

        return updatedOrder;
    }
}