package com.tomato.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class EnrollmentDTO implements Serializable {
	private String no;
	private String type;
	private String name;
	private String dateOfBirth;
	private String college;
	private String major;
	private String grade;
}