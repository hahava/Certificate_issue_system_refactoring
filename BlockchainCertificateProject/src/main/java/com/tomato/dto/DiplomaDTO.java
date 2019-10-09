package com.tomato.dto;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * @author hahava
 *
 */
@Getter
@Setter
public class DiplomaDTO implements Serializable {
	private String no;
	private String type;
	private String name;
	private String dateOfBirth;
	private String college;
	private String major;
	private String dateOfMatriculation;
	private String dateOfGraduation;
	private String nameOfDegree;
	private String degreeRegistrationNo;

	@Override
	public String toString() {
		return "DiplomaDTO [no=" + no + ", type=" + type + ", name=" + name + ", dateOfBirth=" + dateOfBirth
			+ ", college=" + college + ", major=" + major + ", dateOfMatriculation=" + dateOfMatriculation
			+ ", dateOfGraduation=" + dateOfGraduation + ", nameOfDegree=" + nameOfDegree
			+ ", degreeRegistrationNo=" + degreeRegistrationNo + "]";
	}
}