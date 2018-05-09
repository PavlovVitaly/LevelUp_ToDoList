package com.levelup.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TaskStatus {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany
    private List<Task> tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskStatus() {
    }

    public TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
