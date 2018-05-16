package com.levelup.web;

import com.levelup.dao.UsersDao;
import com.levelup.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersBean {
    private final EntityManager em;
    private final UsersDao dao;

    public UsersBean(EntityManager em) {
        this.em = em;
        dao = new UsersDao(em);
    }

    public List<User> getUsers() {
        return dao.findAllUsers();
    }
}
