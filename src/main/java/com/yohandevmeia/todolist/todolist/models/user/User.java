package com.yohandevmeia.todolist.todolist.models.user;

import java.util.HashSet;
import java.util.Set;

import com.yohandevmeia.todolist.todolist.models.task.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<Task>();

    public User(){

    }

    public User(String name) {
        this.name = name;
    }

    public User(UserDTO userDTO) {
        name = userDTO.name();
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can not be null");
        }
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }
    
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setUser(null);
    }
    
}
