package com.tomato.common;

import com.tomato.dto.UserDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * 데이터 베이스 역할을 담당하는 클래스 <br>
 * 상속과 객체 생성을 불가능하게 작성
 *
 * @author hahava
 */
public final class LoginUserData {

	private LoginUserData() {
	}

	// 싱글톤 패턴을 사용하여 객체를 관리한다.
	private final static List<UserDTO> MEMBERS;

	static {
		MEMBERS = new LinkedList<>();
		MEMBERS.add(new UserDTO("halin", "1234"));
	}

	public static List<UserDTO> getInstance() {
		return MEMBERS;
	}
}
