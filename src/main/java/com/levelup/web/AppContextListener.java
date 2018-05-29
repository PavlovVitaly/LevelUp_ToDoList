package com.levelup.web;

import com.levelup.dao.UsersDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("ProductionPersistenceUnitMySql");
        em = emf.createEntityManager();

        createTestUsers();

        servletContextEvent.getServletContext().setAttribute("usersBean", new UsersBean(em));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        em.close();
        emf.close();
    }

    private void createTestUsers() {
        UsersDao dao = new UsersDao(em);

        em.getTransaction().begin();

        for (int i = 0; i < 3; ++i) {
            dao.createUser("login" + String.valueOf(i),
                    "password" + String.valueOf(i),
                    "FN" + String.valueOf(i),
                    "SN" + String.valueOf(i),
                    "email" + String.valueOf(i));
        }

        em.getTransaction().commit();
    }
}
