package com.levelup.dao;

import com.levelup.dao.db_repo.TaskRepository;
import com.levelup.model.Task;
import com.levelup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class TaskDao {
    @Autowired
    TaskRepository taskRepository;

    public Task findByPrimaryKey(int id) {
        return taskRepository.findById(id);
    }

    public Task createTask(String name, String description) {
        Task task = new Task(name, description);
        taskRepository.save(task);
        return task;
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }
}
