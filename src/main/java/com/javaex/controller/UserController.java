package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;

	//로그인 폼
	@RequestMapping(value="/user/loginForm",method={RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController>loginForm");
		
		//주의)view-resolver가 해결해주는 건 views까지다! user 잊지 말기!
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/user/login",method={RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>login");
		System.out.println(userVo);
		
		UserVo authUser = userDao.getUser(userVo);
		
		return "redirect:/mysite4/user/loginForm";
	}
	
	
	
}
