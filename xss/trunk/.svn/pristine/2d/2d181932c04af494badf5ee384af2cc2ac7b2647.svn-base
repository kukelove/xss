package com.xp.brushms.service;

import com.xp.brushms.dao.UserDao;
import com.xp.brushms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hzm on 2015/7/10.
 */
@Repository("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserDao userDao;
    @Override
    public User getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User authenticate(String name, String password) {
        User user = userDao.getUser(name);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
