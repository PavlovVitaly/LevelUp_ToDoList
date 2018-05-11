package com.levelup.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.util.Date;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class DbTest {
    private EntityManagerFactory emf;
    private EntityManager em;

    private String expectedLogin = "login";
    private String expectedPassword = "password";
    private String expectedFirstName = "firstName";
    private String expectedSecondName = "secondName";
    private String expectedEmail = "email";

    private String expectedName = "name";
    private String expectedDescription = "description";

    private String expectedStatus = "status";

    private User insertDefaultUser(String login, String password, String firstName, String secondName, String email) throws Throwable{
        User user = new User(login, password, firstName, secondName, email);
        em.getTransaction().begin();
        try{
            em.persist(user);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }

        return em.find(User.class, user.getId());
    }

    private Task insertDefaultTask(String name, String description) throws Throwable{
        Task task = new Task(name, description, new Date());
        em.getTransaction().begin();
        try{
            em.persist(task);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }

        return em.find(Task.class, task.getId());
    }

    private TaskStatus insertDefaultTaskStatus(String status) throws Throwable{
        TaskStatus taskStatus = new TaskStatus(status);
        em.getTransaction().begin();
        try{
            em.persist(taskStatus);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }

        return em.find(TaskStatus.class, taskStatus.getId());
    }

    private void insertInAllTables(User user, Task task, TaskStatus taskStatus) throws Throwable{
        em.getTransaction().begin();
        try{
            em.persist(user);
            em.persist(taskStatus);
            em.persist(task);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Before
    public void setup(){
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
    }

    @After
    public void end(){
        em.close();
        emf.close();
    }

    @Test
    public void createUserTest() throws Throwable{
        User found = insertDefaultUser(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        assertNotNull(found);
    }

    @Test
    public void createTaskTest() throws Throwable{
        Task found = insertDefaultTask(expectedName, expectedDescription);
        assertNotNull(found);
    }

    @Test
    public void createTaskStatusTest() throws Throwable{
        TaskStatus found = insertDefaultTaskStatus(expectedStatus);
        assertNotNull(found);
    }

    @Test
    public void insertDefaultUserTest() throws Throwable{

        User found = insertDefaultUser(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        assertNotNull(found);
        assertEquals(expectedLogin, found.getLogin());
        assertEquals(expectedPassword, found.getPassword());
        assertEquals(expectedFirstName, found.getFirstName());
        assertEquals(expectedSecondName, found.getSecondName());
        assertEquals(expectedEmail, found.getEmail());
    }

    @Test
    public void insertDefaultTaskTest() throws Throwable{

        Task found = insertDefaultTask(expectedName, expectedDescription);
        assertNotNull(found);
        assertEquals(expectedName, found.getName());
        assertEquals(expectedDescription, found.getDescription());
    }

    @Test
    public void insertDefaultTaskStatusTest() throws Throwable{
        TaskStatus found = insertDefaultTaskStatus(expectedStatus);
        assertNotNull(found);
        assertEquals(expectedStatus, found.getStatus());
    }

    @Test
    public void createAllTables() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = new TaskStatus(expectedStatus);

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task, taskStatus);

        User foundUser = em.find(User.class, user.getId());
        Task foundTask = em.find(Task.class, task.getId());
        TaskStatus foundTaskStatus = em.find(TaskStatus.class, taskStatus.getId());

        assertNotNull(foundUser);
        assertNotNull(foundTask);
        assertNotNull(foundTaskStatus);
    }

    @Test
    public void linkBetweenUserAndTask() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = new TaskStatus(expectedStatus);

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task, taskStatus);

        User foundUser = em.find(User.class, user.getId());
        Task foundTask = em.find(Task.class, task.getId());

        assertEquals(foundUser, foundTask.getOwner());
    }

    @Test
    public void linkBetweenTaskAndTaskStatus() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = new TaskStatus(expectedStatus);

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task, taskStatus);

        Task foundTask = em.find(Task.class, task.getId());
        TaskStatus foundTaskStatus = em.find(TaskStatus.class, taskStatus.getId());

        assertEquals(foundTaskStatus, foundTask.getStatus());
    }

    @Test
    public void linkBetweenTaskAndUser() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = new TaskStatus(expectedStatus);

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task, taskStatus);

        User foundUser = em.find(User.class, user.getId());
        Task foundTask = em.find(Task.class, task.getId());

        assertEquals(foundTask, foundUser.getTasks().get(0));
    }

    @Test
    public void linkBetweenTaskStatusAndTask() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = new TaskStatus(expectedStatus);

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task, taskStatus);

        Task foundTask = em.find(Task.class, task.getId());
        TaskStatus foundTaskStatus = em.find(TaskStatus.class, taskStatus.getId());

        assertEquals(foundTask, foundTaskStatus.getTasks().get(0));
    }
}
