package com.tomato.dao;

import com.tomato.dto.UserDTO;
import com.tomato.util.LoginUserData;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
	/**
	 * 사용자가 입력한 아이디와 패스워드를 확인하는 기능
	 *
	 * @param userDTO
	 * @return boolean
	 */
	public boolean hasCertification(UserDTO userDTO) {
		boolean loginOk = false;
		for (UserDTO user : LoginUserData.getInstance()) {
			if (user.getId().equals(userDTO.getId())) {
				if (user.getPasswd().equals(userDTO.getPasswd())) {
					loginOk = true;
					break;
				}
			}
		}
		return loginOk;
	}
}
