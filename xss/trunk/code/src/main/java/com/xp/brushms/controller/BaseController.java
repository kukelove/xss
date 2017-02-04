package com.xp.brushms.controller;

import com.xp.brushms.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hzm on 2015/7/9.
 */
public class BaseController {
    @Autowired
    protected HttpSession httpSession;
    @Autowired
    protected HttpServletRequest request;

    public LoginUser getLoginUser() {
        return (LoginUser) httpSession.getAttribute("loginUser");
    }

    public void returnFile(String target, byte[] data, HttpServletResponse res) throws IOException {
        OutputStream os = res.getOutputStream();
        try {
            if (target == null) {
                target = "target";
            }
            res.reset();
            res.setHeader("Content-Disposition", "attachment; filename=" + target);
            res.setContentType("application/octet-stream");
            os.write(data);
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
