package com.yohandevmeia.todolist.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yohandevmeia.todolist.todolist.models.user.User;
import com.yohandevmeia.todolist.todolist.models.user.UserDTO;

@SpringBootTest
class TodolistApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void CRUD() throws Exception {
		User user = new User("Fulano");

		// Testing DTO
		UserDTO userDTO = new UserDTO(user);

		// From DTO to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String userDTOJson = objectMapper.writeValueAsString(userDTO);

		// Testing creation
		mockMvc.perform(post("/user").contentType("application/json").content(userDTOJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("Fulano"));
	
		
	
	}
}
