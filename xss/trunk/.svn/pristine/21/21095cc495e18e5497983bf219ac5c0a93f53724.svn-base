package com.xp.brushms.controller;

import com.xp.brushms.common.Config;
import com.xp.brushms.common.Constants;
import com.xp.brushms.entity.User;
import com.xp.brushms.service.UserService;
import com.xp.brushms.util.CryptUtils;
import com.xp.brushms.vo.LoginUser;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Enumeration;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@Autowired
	protected UserService userService;

	@Autowired
	private Config config;


	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		LoginUser loginUser = getLoginUser();
		return "index";
	}

	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public String indexPage() {
		LoginUser loginUser = getLoginUser();
		return "index";	
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping("/login.api")
	@ResponseBody
	public Msg login(String name, String password) {
		User user = userService.authenticate(name, CryptUtils.getMd5(password));
		if (user != null) {
			LoginUser loginUser = new LoginUser();
			if (user.getName().equals("admin") && user.getRole() == null) {
				user.setRole(Constants.RoleAdmin);
			}
			loginUser.setRole(user.getRole());
			loginUser.setUserName(user.getName());
			httpSession.setAttribute("loginUser", loginUser);
		}
		return Msg.create(user != null?0:1, null, null);
	}
	@RequestMapping("/logout.html")
	public String logout() {

		Enumeration<String> attributeNames = httpSession.getAttributeNames();
		if(attributeNames != null) {
			while (attributeNames.hasMoreElements()) {
				String key = attributeNames.nextElement();
				httpSession.removeAttribute(key);
			}
		}
		return "redirect:" + config.getLoginUrl();
	}






}