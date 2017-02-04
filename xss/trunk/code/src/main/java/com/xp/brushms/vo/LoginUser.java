package com.xp.brushms.vo;

import java.io.Serializable;

/**
 * Created by hzm on 2015/7/10.
 */

public class LoginUser implements Serializable {
    private String role;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
