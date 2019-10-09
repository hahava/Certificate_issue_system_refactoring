package com.tomato.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EmailDTO;
import com.tomato.dto.EnrollmentDTO;
import com.tomato.service.CertificationService;
import com.tomato.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	private EmailSender emailSender;

	@Autowired
	private CertificationService certificationService;

	@Autowired
	private ObjectMapper objectMapper;

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity emailExceptionHandler() {
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/emailSender", method = RequestMethod.POST)
	public String showResult(HttpSession session, @RequestParam String emailReceiver) throws Exception {

		String time = (String)session.getAttribute("time");
		String userId = (String)session.getAttribute("loginOk");

		EmailDTO email = new EmailDTO();
		email.setReceiver(emailReceiver);
		email.setSubject("세종대학교 증명 발급 신청");
		email.setUserId(userId);
		email.setRequestTime(time);

		EnrollmentDTO enrollment = (EnrollmentDTO)session.getAttribute("enrollment");
		if (enrollment != null) {
			email.setCertificationResult(objectMapper.writeValueAsString(certificationService.getEnlloment(userId)));
			emailSender.sendEmail(email, "enrollment");
		}

		DiplomaDTO diploma = (DiplomaDTO)session.getAttribute("diploma");
		if (diploma != null) {
			email.setCertificationResult(objectMapper.writeValueAsString(certificationService.getDiploma(userId)));
			emailSender.sendEmail(email, "diploma");
		}

		session.invalidate();
		return "cmmn/saveDataSuccess";
	}

}
