package com.tomato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
	private String subject;
	private String content;
	private String regdate;
	private String reciver;
}
