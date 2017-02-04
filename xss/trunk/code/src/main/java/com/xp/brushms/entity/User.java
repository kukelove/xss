package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by hzm on 2015/7/10.
 */
public class User {
    @Id
    private String name;
    private String password;
    private Date created;
    private String role;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
