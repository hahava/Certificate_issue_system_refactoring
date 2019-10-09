import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomato.dto.DiplomaDTO;
import org.junit.Before;

import java.io.IOException;

public class Test {
	private DiplomaDTO diplomaDTO;

	@Before
	public void setUp() {
		diplomaDTO = new DiplomaDTO();
		diplomaDTO.setCollege("sejong");
		diplomaDTO.setDateOfBirth("199920921");
		diplomaDTO.setDateOfGraduation("20190909");
		diplomaDTO.setDateOfMatriculation("19920921");
		diplomaDTO.setMajor("cs");
		diplomaDTO.setNo("1");
		diplomaDTO.setType("졸업 증명서");
		diplomaDTO.setNameOfDegree("공학사");
		diplomaDTO.setName("halin Lee");
		diplomaDTO.setDegreeRegistrationNo("세종대2018(학) 4947");
	}

	@org.junit.Test
	public void jsonConverterTest() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String result = mapper.writeValueAsString(diplomaDTO);
			System.out.println(result);
			DiplomaDTO dto = mapper.readValue(result, DiplomaDTO.class);
			System.out.println(dto.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
