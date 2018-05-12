package com.levelup.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    private User manager;

    @Column(nullable = false)
    private Role role = Role.EMPLOYEE;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<User> subordinates;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Task> tasks;

    public User() {
    }

    public User(String login, String password, String firstName, String secondName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<User> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
