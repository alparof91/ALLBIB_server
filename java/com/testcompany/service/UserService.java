package com.testcompany.service;

import com.testcompany.dao.UserDao;
import com.testcompany.entity.User;

import javax.persistence.Persistence;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        try {
            userDao = new UserDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addUser (User newUser) {
        userDao.create(newUser);
    }

    public void updateUser (User updatedUser) {
        userDao.update(updatedUser);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    //for login
    public User findUser(String user, String pass) throws Exception {
        List<User> users = userDao.find(user);
        if (users.size() == 0) {
            throw new Exception("User not found");
        }
        User u = users.get(0);

        if (pass.compareTo(u.getPassword()) != 0) {
            throw new Exception("Password does not match");
        }
        return u;
    }
}
