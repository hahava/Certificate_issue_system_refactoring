package com.tomato.controller;

import com.tomato.dto.UserDTO;
import com.tomato.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 로그인 관련 페이지를 제어하는 클래스
 *
 * @author hahava
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// index 페이지 요청
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	// login 화면 요청
	@RequestMapping(value = "/loginPage.do", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	// 로그인 확인
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(UserDTO userDTO, HttpSession session, ModelAndView mv) {
		boolean loginOk = loginService.hasCertification(userDTO);

		if (loginOk == false) {
			mv.setViewName("redirect:/loginPage.do");
			mv.addObject("login", false);
			return mv;
		}

		// 로그인 세션추가
		session.setAttribute("loginOk", userDTO.getId());
		mv.setViewName("check");

		return mv;
	}
}
