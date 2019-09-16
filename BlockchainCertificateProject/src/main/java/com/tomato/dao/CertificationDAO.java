package com.tomato.dao;

import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EnrollmentDTO;
import com.tomato.util.JsonUtil;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public class CertificationDAO {

	public EnrollmentDTO getEnlloment(String userId) {

		JSONObject jsonObject = (JSONObject)JsonUtil.getJsonObject(userId);
		JSONObject dataObject = (JSONObject)jsonObject.get("certificate1");

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

	public DiplomaDTO getDiploma(String userId) {
		JSONObject jsonObject = (JSONObject)JsonUtil.getJsonObject(userId);
		JSONObject dataObject = (JSONObject)jsonObject.get("certificate2");

		DiplomaDTO diplomaDTO = new DiplomaDTO();
		diplomaDTO.setNo(dataObject.get("no").toString());
		diplomaDTO.setType(dataObject.get("type").toString());
		diplomaDTO.setName(dataObject.get("name").toString());
		diplomaDTO.setDateOfBirth(dataObject.get("dateOfBirth").toString());
		diplomaDTO.setCollege(dataObject.get("college").toString());
		diplomaDTO.setMajor(dataObject.get("major").toString());
		diplomaDTO.setDateOfMatriculation(dataObject.get("dateOfMatriculation").toString());
		diplomaDTO.setDateOfGraduation(dataObject.get("dateOfGraduation").toString());
		diplomaDTO.setNameOfDegree(dataObject.get("nameOfDegree").toString());
		diplomaDTO.setDegreeRegistrationNo(dataObject.get("degreeRegistrationNo").toString());
		return diplomaDTO;
	}

}
