package com.levelup.dao;

import com.levelup.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersDao {
    private final EntityManager em;

    public UsersDao(EntityManager em){
        this.em = em;
    }

    public User findByPrimaryKey(int id) {
        return em.find(User.class, id);
    }

    public User createUser(String login, String password, String firstName, String secondName, String email) {
        User user = new User(login, password, firstName, secondName, email);
        em.persist(user);
        return user;
    }

//    @SuppressWarnings("unchecked")
//    public List<Part> findByPartId(String partId) {
//        return em.createNamedQuery(Part.SEARCH_BY_PART_ID)
//                .setParameter("partId", partId)
//                .getResultList();
//    }
}
