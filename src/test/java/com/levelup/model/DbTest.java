package com.levelup.model;

import com.levelup.dao.TaskDao;
import com.levelup.dao.UsersDao;

import com.levelup.model.conf.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.util.Date;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DbTest {

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private TaskDao taskDao;

    private String expectedLogin = "login";
    private String expectedPassword = "password";
    private String expectedFirstName = "firstName";
    private String expectedSecondName = "secondName";
    private String expectedEmail = "email";

    private String expectedName = "name";
    private String expectedDescription = "description";

    private User insertDefaultUser(String login, String password, String firstName, String secondName, String email) throws Throwable{
        User user = usersDao.createUser(login, password, firstName, secondName, email);
        return usersDao.findByPrimaryKey(user.getId());
    }

    private Task insertDefaultTask(String name, String description) throws Throwable{
        Task task = taskDao.createTask(name, description);
        return taskDao.findByPrimaryKey(task.getId());
    }

    private void insertInAllTables(User user, Task task) throws Throwable{
        usersDao.saveUser(user);
        taskDao.saveTask(task);
    }

//    @Before
//    public void setup(){
//        usersDao = new UsersDao();
//        taskDao = new TaskDao();
//    }
//
//    @After
//    public void end(){
//        usersDao = null;
//        taskDao = null;
//    }

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
    public void createAllTables() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription);
        TaskStatus taskStatus = TaskStatus.ACTIVE;

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task);

        User foundUser = usersDao.findByPrimaryKey(user.getId());
        Task foundTask = taskDao.findByPrimaryKey(task.getId());

        assertNotNull(foundUser);
        assertNotNull(foundTask);
    }

    @Test
    public void linkBetweenUserAndTask() throws Throwable{

        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
        Task task = new Task(expectedName, expectedDescription, new Date());
        TaskStatus taskStatus = TaskStatus.ACTIVE;

        task.setOwner(user);
        task.setStatus(taskStatus);

        insertInAllTables(user, task);

        User foundUser = usersDao.findByPrimaryKey(user.getId());
        Task foundTask = taskDao.findByPrimaryKey(task.getId());

        assertEquals(foundUser, foundTask.getOwner());
    }

//    @Test
//    public void linkBetweenTaskAndUser() throws Throwable{
//
//        User user = new User(expectedLogin, expectedPassword, expectedFirstName, expectedSecondName, expectedEmail);
//        Task task = new Task(expectedName, expectedDescription, new Date());
//        TaskStatus taskStatus = TaskStatus.ACTIVE;
//
//        task.setOwner(user);
//        task.setStatus(taskStatus);
//
//        insertInAllTables(user, task);
//
////        User foundUser = em.find(User.class, user.getId());
//        User foundUser = (User)em.createQuery("FROM User AS us LEFT JOIN FETCH us.tasks AS tsk WHERE us.id = :id")
//                .setParameter("id", user.getId())
//                .getSingleResult();
//        Task foundTask = em.find(Task.class, task.getId());
//
//        assertEquals(foundTask, foundUser.getTasks().get(0));
//    }

}
