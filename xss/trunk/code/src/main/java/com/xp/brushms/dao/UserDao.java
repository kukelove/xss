package com.xp.brushms.dao;


import com.xp.brushms.entity.User;

/**
 * Created by hzm on 2015/7/10.
 */
public interface UserDao {
    public User getUser(String name);
    public void saveUser(User user);
}
