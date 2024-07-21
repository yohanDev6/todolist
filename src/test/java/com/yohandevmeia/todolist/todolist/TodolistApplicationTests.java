package com.yohandevmeia.todolist.todolist;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yohandevmeia.todolist.todolist.models.task.Priorities;
import com.yohandevmeia.todolist.todolist.models.task.Task;
import com.yohandevmeia.todolist.todolist.models.task.TaskRepository;
import com.yohandevmeia.todolist.todolist.models.user.User;
import com.yohandevmeia.todolist.todolist.models.user.UserRepository;

@SpringBootTest
class TodolistApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void crudUserTest() {

		//Create
		User user = new User();
		user.setName("Beltrano");
		user = userRepository.save(user);
		
		//Read
		Optional<User> foundUser = userRepository.findById(user.getId());
		foundUser.get().getName();

		//Update
		user.setName("Cicrano");
		user = userRepository.save(user);

		//Delete
		userRepository.delete(user);
		foundUser = userRepository.findById(user.getId());
	}

	@Test
	void crudTaskTest() {
		User user = new User();
		user.setName("TesteJoao");
		
		//Create
		Task task1 = new Task("Estudar JAVA", "Criar um projeto usando o spring boot", Priorities.BAIXA);
		Task task2 = new Task("Dormir", "Dormir para ter uma saúde melhor", Priorities.ALTA);
		
		user.addTask(task1);
		user.addTask(task2);

		user = userRepository.save(user);

		//Update
		task1.setFinished(true);
		task1 = taskRepository.save(task1);

		//Delete all
		userRepository.delete(user);
	}
}
