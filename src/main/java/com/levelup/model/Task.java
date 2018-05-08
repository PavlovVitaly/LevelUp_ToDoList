package com.levelup.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
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

    @OneToOne
    private TaskStatus status;

    @ManyToOne
    private User owner;

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
}
