package com.yohandevmeia.todolist.todolist.models.user;

import java.util.ArrayList;
import java.util.List;

public record UserDTO(String name) {
    
    public UserDTO(User user) {
        this(user.getName());
    }

    public static ArrayList<UserDTO> objectToDTO(List<User> users) {
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
}