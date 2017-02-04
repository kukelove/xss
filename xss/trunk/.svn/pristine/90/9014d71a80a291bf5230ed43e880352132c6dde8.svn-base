package com.xp.brushms.intercepter;

import com.xp.brushms.auth.NoAuth;
import com.xp.brushms.auth.NoRequireRole;
import com.xp.brushms.auth.RequireRole;
import com.xp.brushms.common.Config;
import com.xp.brushms.util.DateUtils;
import com.xp.brushms.vo.LoginUser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hzm on 2015/5/13.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    protected Config config;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private void responseLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String returnUrl = req.getRequestURI();
        if (!StringUtils.isEmpty(req.getQueryString())) {
            returnUrl += "?" + req.getQueryString();
        }
        resp.sendRedirect(config.getLoginUrl() + "?returnUrl=" + URLEncoder.encode(returnUrl, "utf-8"));
    }
    private void responseForbidden(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.sendError(403);
    }
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        if (! (o instanceof HandlerMethod)) return true;
        HandlerMethod method = (HandlerMethod)o;

        if (method.getMethod().getName().startsWith("register")||method.getMethod().getName().startsWith("login") || method.getMethod().getName().startsWith("logout") || method.getMethod().getName().startsWith("error")) {
            return true;
        }


        Set<String> roles = new HashSet<String>();

        RequireRole rr = null;
        boolean noauth = false;
        boolean requireNoRole = false;
        try {
            rr = method.getMethodAnnotation(RequireRole.class);
        } catch (Exception exp) {}
        if (rr != null) {
            for (String v : rr.value()) {
                roles.add(v);
            }
        }
        try {
            if (method.getMethodAnnotation(NoAuth.class) != null) {
                noauth = true;
                roles.clear();
            }
        } catch (Exception e) {}
        try {
            if (method.getMethodAnnotation(NoRequireRole.class) != null) {
                requireNoRole = true;
            }
        } catch (Exception e) {}
        rr = null;
        try {
            rr = method.getBeanType().getAnnotation(RequireRole.class);
        } catch (Exception exp) {}
        if (rr != null) {
            for (String v : rr.value()) {
                roles.add(v);
            }
        }
        try {
            if (method.getBeanType().getAnnotation(NoAuth.class) != null) {
                noauth = true;
                roles.clear();
            }
        } catch (Exception e) {}

        if (roles.size() <= 0 && noauth) {
            return true;
        }
        LoginUser loginUser = (LoginUser)req.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            logger.info(DateUtils.getDateTimeStr(new Date()) + " [action-log] " + loginUser.getUserName() + " " + method.getBean().getClass().getSimpleName() + ":"
                    + method.getMethod().getName() + " " + req.getQueryString() + " " + req.getHeader("Referer"));
            return roles.size() == 0 || requireNoRole || roles.contains(loginUser.getRole());
        }
        responseLogin(req, resp);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
