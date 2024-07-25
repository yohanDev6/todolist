package com.yohandevmeia.todolist.todolist.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.yohandevmeia.todolist.todolist.controllers.restcontrollers.UserController;
import com.yohandevmeia.todolist.todolist.models.user.User;
import com.yohandevmeia.todolist.todolist.models.user.UserDTO;

@Controller
@RequestMapping("/")
public class DefaultController {
	
	private final RestTemplate restTemplate;

    @Autowired
    public DefaultController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
	@GetMapping
	public String index(Model model) {
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity("http://localhost:8080/user", UserDTO[].class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
            List<UserDTO> usersDTO = Arrays.asList(response.getBody());
            //ArrayList<UserDTO> usersDTO = UserDTO.objectToDTO(users);
            model.addAttribute("users", usersDTO);
        } else {
            model.addAttribute("error", "Erro ao carregar usuários: " + response.getStatusCode());
        }
		return "index";
	}
}
