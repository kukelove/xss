package com.xp.brushms.controller;

import com.xp.brushms.common.Constants;
import com.xp.brushms.entity.User;
import com.xp.brushms.service.UserService;
import com.xp.brushms.util.CryptUtils;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by hzm on 2015/7/10.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    protected UserService userService;

    @RequestMapping("/create_user.api")
    @ResponseBody
    public Msg createUser(String name, String password) {
        if (StringUtils.isEmpty(name)) {
            return Msg.create(1, "用户名不能为空", null);
        }
        if (StringUtils.isEmpty(password)) {
            return Msg.create(2, "密码不能为空", null);
        }
        User user = new User();
        user.setName(name);
        user.setPassword(CryptUtils.getMd5(password));
        user.setCreated(new Date());
        user.setRole(Constants.RoleAdmin);
        userService.addUser(user);
        return Msg.create();
    }
}
