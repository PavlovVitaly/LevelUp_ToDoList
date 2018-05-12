package com.levelup.dao;

import com.levelup.model.Task;
import com.levelup.model.User;

import javax.persistence.EntityManager;

public class TaskDao {
    private final EntityManager em;

    public TaskDao(EntityManager em){
        this.em = em;
    }

    public Task findByPrimaryKey(int id) {
        return em.find(Task.class, id);
    }

    public Task createTask(String name, String description) {
        Task task = new Task(name, description);
        em.persist(task);
        return task;
    }
}
