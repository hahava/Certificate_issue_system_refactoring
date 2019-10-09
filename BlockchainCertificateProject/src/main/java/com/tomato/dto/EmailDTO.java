package com.tomato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
	private String userId;
	private String requestTime;
	private String certificationResult;
	private String subject;
	private String content;
	private String receiver;
}
