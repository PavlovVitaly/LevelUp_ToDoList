package com.levelup.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dateOfCreating;

    @Column
    private Date deadlineDate;

    @Column
    private Date startDateOfExecution;

    @Column
    private Date endDateOfExecution;

    @Column
    private TaskStatus status = TaskStatus.NEW;

//    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User owner;

    public Task() {
    }

    public Task(String name, String description, Date dateOfCreating) {
        this.name = name;
        this.description = description;
        this.dateOfCreating = dateOfCreating;
    }

    public Task(String name, String description) {
        this(name, description, new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Date dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getStartDateOfExecution() {
        return startDateOfExecution;
    }

    public void setStartDateOfExecution(Date startDateOfExecution) {
        this.startDateOfExecution = startDateOfExecution;
    }

    public Date getEndDateOfExecution() {
        return endDateOfExecution;
    }

    public void setEndDateOfExecution(Date endDateOfExecution) {
        this.endDateOfExecution = endDateOfExecution;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(dateOfCreating, task.dateOfCreating) &&
                Objects.equals(deadlineDate, task.deadlineDate) &&
                Objects.equals(startDateOfExecution, task.startDateOfExecution) &&
                Objects.equals(endDateOfExecution, task.endDateOfExecution) &&
                status == task.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, dateOfCreating, deadlineDate, startDateOfExecution, endDateOfExecution, status);
    }
}
