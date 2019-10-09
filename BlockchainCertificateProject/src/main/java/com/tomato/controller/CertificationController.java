package com.tomato.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EnrollmentDTO;
import com.tomato.service.CertificationService;
import com.tomato.util.BlockChainNetwork;
import com.tomato.util.StringUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * 인증과 관련된 기능을 처리
 *
 * @author hahava
 */
@Controller
@Log
public class CertificationController {

	@Autowired
	private CertificationService certificationService;

	@Autowired
	private ObjectMapper objectMapper;

	// json 방식으로 보낸 요청이 서버에 있는 데이터와 맞는지 검증한다.
	@RequestMapping(value = "/resultCheck.do", method = RequestMethod.GET)
	public ModelAndView resultCheck(HttpServletRequest request, ModelAndView mv) {

		EnrollmentDTO enrollmentDTO = (EnrollmentDTO)request.getSession().getAttribute("check_enrollment");
		DiplomaDTO diplomaDTO = (DiplomaDTO)request.getSession().getAttribute("check_diploma");
		String time = (String)request.getSession().getAttribute("check_time");

		String blockValue = time;
		if (enrollmentDTO != null) {
			blockValue += enrollmentDTO.toString();
		}

		if (diplomaDTO != null) {
			blockValue += diplomaDTO.toString();
		}

		if (!BlockChainNetwork.checkMap(time, blockValue)) {
			mv.addObject("isTrue", Boolean.FALSE);
		} else {
			mv.addObject("isTrue", Boolean.TRUE);
		}
		mv.setViewName("checkerResult");
		return mv;
	}

	@RequestMapping(value = "/certification.do/{type}/{result}/{timeData}", method = RequestMethod.GET)
	public ModelAndView diplomaCertification(@PathVariable String type, @PathVariable String result,
		@PathVariable String timeData, ModelAndView mv) {
		try {
			if (type.equals("diploma")) {
				DiplomaDTO diplomaDTO = objectMapper.readValue(result, DiplomaDTO.class);
				mv.addObject("diploma", diplomaDTO);
			} else if (type.equals("enrollment")) {
				EnrollmentDTO enrollmentDTO = objectMapper.readValue(result, EnrollmentDTO.class);
				mv.addObject("enrollment", enrollmentDTO);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		mv.addObject("time", timeData);
		mv.setViewName("checker");
		return mv;
	}

	// 체크박스에 체크가 되어 있는 항목을 json 파일에서 파싱한다.(재학, 졸업 증명)
	@RequestMapping(value = "/boxcheck.do")
	public ModelAndView boxCheck(HttpServletRequest request, ModelAndView mv) {

		String userId = request.getSession().getAttribute("loginOk").toString();
		String[] value = request.getParameterValues("checkbox");
		String time = StringUtil.getDateTime();
		String blockValue = null;

		// 체크 박스에 체크가 하나 이상 되지 않을 경우 리턴한다. (자바스크립트가 적용안될 경우 대비)
		if (value == null) {
			mv.setViewName("check");
			return mv;
		}

		if (Arrays.stream(value).anyMatch("certification"::equals)) {
			EnrollmentDTO enrollmentDTO = certificationService.getEnlloment(userId);
			blockValue += enrollmentDTO.toString();
			mv.addObject("enrollment", enrollmentDTO);
		}

		if (Arrays.stream(value).anyMatch("diploma"::equals)) {
			DiplomaDTO diplomaDTO = certificationService.getDiploma(userId);
			blockValue += diplomaDTO.toString();
			mv.addObject("diploma", diplomaDTO);
		}

		BlockChainNetwork.addHashMap(time, blockValue);

		request.getSession().setAttribute("time", time);

		mv.addObject("timestamp", time);
		mv.setViewName("result");

		return mv;
	}
}