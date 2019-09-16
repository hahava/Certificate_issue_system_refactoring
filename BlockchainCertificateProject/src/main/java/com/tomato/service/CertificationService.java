package com.tomato.service;

import com.tomato.dao.CertificationDAO;
import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CertificationService {

	@Autowired
	private CertificationDAO certificationDAO;

	public EnrollmentDTO getEnlloment(String userId) {
		return certificationDAO.getEnlloment(userId);
	}

	public DiplomaDTO getDiploma(String userId) {
		return certificationDAO.getDiploma(userId);
	}

}
