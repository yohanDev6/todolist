package com.yohandevmeia.todolist.todolist.models.user;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    public User(long id, String name) {
        if (id > 0 && !name.trim().isEmpty()) {
            this.id = id;
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name or id");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can not be null");
        }
    }
}
