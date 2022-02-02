package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value = "loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";

	}

	// 로그인
	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		// System.out.println(userVo);

		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);

		if (authUser != null) { // 로그인 성공
			// 세션에 저장
			session.setAttribute("authUser", authUser);

			// 리다이렉트 메인
			return "redirect:/";
		} else { // 로그인 실패
			// 리다이렉트 로그인 폼
			return "redirect:/user/loginForm?result=fail";

		}

	}

	// 로그아웃
	@RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");

		// 세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	// 회원가입 폼
	@RequestMapping(value = "joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping("checkId")
	public int checkId(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.checkId()]");
		//System.out.println(userVo);
		
		UserVo checkUser = userService.checkId(userVo);
		
		if(checkUser != null) {
			return 1;
		} else {
			return 0;
		}
		
	}

	// 회원가입
	@RequestMapping(value = "join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");
		System.out.println(userVo);

		userService.join(userVo);

		return "user/joinOk";

	}

	// 회원정보 수정폼
	@RequestMapping(value = "userModifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String userModifyForm(HttpSession session, Model model) {
		System.out.println("[UserController.userModifyForm()]");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		
		model.addAttribute("userVo", userVo);
		return "user/modifyForm";
	}

	// 회원정보 수정
	@RequestMapping(value = "modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.modify()]");
		
		userService.modify(userVo);
		
		return "redirect:/";
	}

	
}
