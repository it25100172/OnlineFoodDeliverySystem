package com.fooddelivery.onlinefoodorderingsystem.Services;

import com.fooddelivery.onlinefoodorderingsystem.Models.User;import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final String FILE_NAME = "src/main/resources/DB/users.txt";

    // READ USERS FROM FILE
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(FILE_NAME));

            String line;

            while((line = br.readLine()) != null) {

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = line.split(",");

                if(data.length < 4){
                    continue;
                }

                User user = new User(
                        (data[0]),
                        data[1],
                        data[2],
                        data[3]
                );

                userList.add(user);
            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return userList;
    }
    public String generateUserId(String role){

        int count = 1;

        List<User> users = getAllUsers();

        for(User user : users){

            if(user.getRole().equalsIgnoreCase(role)){
                count++;
            }
        }

        if(role.equalsIgnoreCase("CUSTOMER")){
            return "C" + count;
        }

        if(role.equalsIgnoreCase("DELIVERY")){
            return "D" + count;
        }

        if(role.equalsIgnoreCase("RESTAURANT")){
            return "R" + count;
        }

        return "U" + count;
    }

    // ADD USER TO FILE
    public User addUser(User user) {

        List<User> userList =
                getAllUsers();

        // CHECK SAME EMAIL + SAME ROLE
        // CHECK EXISTING ACCOUNT
        for(User existingUser : userList){

            // SAME EMAIL + SAME PASSWORD
            if(existingUser.getEmail()
                    .equalsIgnoreCase(
                            user.getEmail()
                    )

                    &&

                    existingUser.getPassword()
                            .equals(
                                    user.getPassword()
                            )){

                return null;
            }
        }

        user.setUserId(
                generateUserId(
                        user.getRole()
                )
        );

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    FILE_NAME,
                                    true
                            )
                    );

            bw.write(
                    user.getUserId() + "," +
                            user.getEmail() + "," +
                            user.getPassword() + "," +
                            user.getRole()
            );

            bw.newLine();

            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    // LOGIN
    public User login(String email, String password) {

        List<User> userList = getAllUsers();

        for(User user : userList) {

            if(user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }


}