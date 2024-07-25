package com.yohandevmeia.todolist.todolist.controllers.restcontrollers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yohandevmeia.todolist.todolist.models.task.Task;
import com.yohandevmeia.todolist.todolist.models.task.TaskDTO;
import com.yohandevmeia.todolist.todolist.models.task.TaskRepository;
import com.yohandevmeia.todolist.todolist.models.user.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("task/")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
			return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
		}
		try {
			//Converting taskDTO in to a new task object
			Task task = new Task(taskDTO);
			
			//Searching and setting a valid user. If work, proceed. Else, throw a new exception
			task.setUser(userRepository.findById(taskDTO.userId()).orElseThrow(() -> new NoSuchElementException("User not found")));
			
			//Saving task
			taskRepository.save(task);
			
			//Returning a good message
			return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
		} catch (IllegalArgumentException iae) {
			
			//If any data is invalid, return a bad message
			return new ResponseEntity<>("Invalid data: " + iae.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			//Generic exception
			return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> readByUser(@PathVariable long userId){
		try {
			List<Task> tasks = taskRepository.findByUserId(userId);
			
			if(tasks.size() > 0) {
				return new ResponseEntity<>(TaskDTO.objectToDTO(tasks), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Tasks not found for this user", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
			return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Task task = new Task(taskDTO);
			taskRepository.save(task);
			return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<>("Invalid data: " + iae.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			taskRepository.deleteById(id);
			return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<>("Id must be > 0", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
