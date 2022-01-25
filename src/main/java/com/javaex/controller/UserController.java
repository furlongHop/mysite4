package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// el 문법: ${pageContext.request.contextPath} --> 컨트롤러에서 사용 불가능, view에서 사용
	// controller에서 session 다루는 이유: server와 연결되어 있어서(인터넷과 상관없는 DB-DAO와 분리 목적)

	// 로그인 폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController>loginForm");

		// 주의)view-resolver가 해결해주는 건 views까지다! user 잊지 말기!
		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>login");
		System.out.println(userVo);

		UserVo authUser = userService.getUser(userVo);
		System.out.println(authUser);

		if (authUser != null) {// 로그인 성공
			System.out.println("로그인 성공");

			// 세션 주소를 받아 세션 어트리뷰트에 로그인한 유저 정보 저장
			session.setAttribute("authUser", authUser);
		
			// 메인으로 리다이렉트
			return "redirect:/main";
		} else {// 로그인 실패

			// 로그인 폼으로 리다이렉트
			return "redirect:/user/loginForm?result=fail";
		}

	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController>logout");

		// 저장된 세션 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController>joinForm");

	

		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>join");

		userService.join(userVo);

		return "user/joinOk";
	}
	
	//회원 정보 수정폼
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, Model model) {
		System.out.println("UserController>modify");

		userService.modify(userVo);
		
		UserVo authUser = userService.getUser(userVo);
		model.addAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm() {
		System.out.println("UserController>modiftForm");

		

		return "user/modifyForm";
	}
}
