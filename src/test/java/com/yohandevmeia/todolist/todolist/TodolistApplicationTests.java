package com.yohandevmeia.todolist.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yohandevmeia.todolist.todolist.models.task.Priorities;
import com.yohandevmeia.todolist.todolist.models.task.Task;
import com.yohandevmeia.todolist.todolist.models.user.User;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void instanceTest() {
		User user = new User(1, "Fulano");
		Task task1 = new Task(1, "Estudar java", "Praticar testes unitários", Priorities.MODERADA);
		user.getName();
		task1.getTitle();
	}
}
