package com.yohandevmeia.todolist.todolist.models.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title, description;
    private Priorities priority;
    private boolean finished;

    public Task(long id, String title, String description, Priorities priority, boolean finished){
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.finished = finished;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Priorities getPriority() {
        return priority;
    }
    public void setPriority(Priorities priority) {
        this.priority = priority;
    }

    public boolean getFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
}
