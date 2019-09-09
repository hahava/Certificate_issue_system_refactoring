package com.tomato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomato.dao.LoginDAO;
import com.tomato.dto.UserDTO;

@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;

	public boolean hasCertification(UserDTO userDTO) {
		return loginDAO.hasCertification(userDTO);
	}
}
