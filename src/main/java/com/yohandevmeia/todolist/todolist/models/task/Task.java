package com.yohandevmeia.todolist.todolist.models.task;

import com.yohandevmeia.todolist.todolist.models.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title, description;
	private String priority;
	private boolean finished = false;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Task() {

	}
	
	public Task(TaskDTO taskDTO) {
		title = taskDTO.title();
		description = taskDTO.description();
		priority = Priorities.enumToString(taskDTO.priority());
		finished = taskDTO.finished();
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title can not be null");
		} else {
			this.title = title;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description can not be null");
		} else {
			this.description = description;
		}
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(Priorities priority) {
		if (priority == null) {
			throw new IllegalArgumentException("Priority can not be null");
		} else {
			this.priority = Priorities.enumToString(priority);
		}
	}

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
