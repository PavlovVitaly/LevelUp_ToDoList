package com.levelup.dao;

import com.levelup.dao.db_repo.UserRepository;
import com.levelup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UsersDao {
    @Autowired
    UserRepository userRepository;

    public User findByPrimaryKey(int id) {
        return userRepository.findById(id);
    }

    public User createUser(String login, String password, String firstName, String secondName, String email) {
        User user = new User(login, password, firstName, secondName, email);
        userRepository.save(user);
        return user;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
