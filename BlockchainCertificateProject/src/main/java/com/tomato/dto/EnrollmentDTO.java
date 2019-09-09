package com.tomato.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;

@Getter
@Setter
@ToString
public class EnrollmentDTO {
	private String no;
	private String type;
	private String name;
	private String dateOfBirth;
	private String college;
	private String major;
	private String grade;

	public static EnrollmentDTO getJsonToDto(JSONObject dataObject) {
		EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
		enrollmentDTO.setNo(dataObject.get("no").toString());
		enrollmentDTO.setType(dataObject.get("type").toString());
		enrollmentDTO.setName(dataObject.get("name").toString());
		enrollmentDTO.setDateOfBirth(dataObject.get("dateOfBirth").toString());
		enrollmentDTO.setCollege(dataObject.get("college").toString());
		enrollmentDTO.setMajor(dataObject.get("major").toString());
		enrollmentDTO.setGrade(dataObject.get("grade").toString());
		return enrollmentDTO;

	}

}