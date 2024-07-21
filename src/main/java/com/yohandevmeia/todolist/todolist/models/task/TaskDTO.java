package com.yohandevmeia.todolist.todolist.models.task;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TaskDTO(
		long id,
		
		@NotBlank(message = "Title can not be null")
		String title,
		
		@NotBlank(message = "Description can not be null")
		String description,
		
		@NotNull(message = "Priority can not be null")
		Priorities priority, 
		
		boolean finished,
		
		@Positive(message = "UserId must be positive")
		long userId) {

	public TaskDTO(Task task) {
		this(task.getId(), task.getTitle(), task.getDescription(), Priorities.stringToEnum(task.getPriority()), task.getFinished(), task.getUser().getId());
	}

	public static ArrayList<TaskDTO> objectToDTO(List<Task> tasks) {
		ArrayList<TaskDTO> tasksDTO = new ArrayList<>();
		for(Task task : tasks) {
			TaskDTO taskDTO = new TaskDTO(task);
			tasksDTO.add(taskDTO);
		}
		return tasksDTO;
	}
}
