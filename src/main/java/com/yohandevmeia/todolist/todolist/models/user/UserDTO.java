/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandevmeia.todolist.todolist.models.user;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yohan
 */
public record UserDTO(long id, String name) {

    public UserDTO(User user){
        this(user.getId(), user.getName());
    }
    
    public static ArrayList<UserDTO> objectToDTO(List<User> users){
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = new UserDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
}
