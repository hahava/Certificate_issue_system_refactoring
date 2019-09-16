package com.tomato.controller;

import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EmailDTO;
import com.tomato.dto.EnrollmentDTO;
import com.tomato.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 인증 정보를 사용자 이메일 주소로 보내주는 기능
 *
 * @author hahava
 */
@Controller
public class EmailController {

	@Autowired
	public EmailSender emailSender;

	@RequestMapping(value = "/emailSender", method = RequestMethod.POST)
	public String showResult(HttpSession session, @RequestParam String emailReceiver) throws Exception {

		String time = (String)session.getAttribute("time");
		EnrollmentDTO enrollment = (EnrollmentDTO)session.getAttribute("enrollment");

		if (enrollment != null) {
			EmailDTO email = new EmailDTO();
			email.setReciver(emailReceiver);
			email.setSubject("세종대학교 증명 발급 신청");
			emailSender.SendEmail(email);
		}

		DiplomaDTO diploma = (DiplomaDTO)session.getAttribute("diploma");
		session.invalidate();

		return "cmmn/saveDataSuccess";
	}

}
