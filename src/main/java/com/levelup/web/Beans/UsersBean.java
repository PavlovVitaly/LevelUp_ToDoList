package com.levelup.web.Beans;

import com.levelup.dao.UsersDao;
import com.levelup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UsersBean {
    @Autowired
    private UsersDao dao;

    public List<User> getUsers() {
        return dao.findAllUsers();
    }
}
