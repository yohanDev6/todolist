package com.yohandevmeia.todolist.todolist.models.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByUserId(long userId);
}
