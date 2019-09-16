package com.tomato.util;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public final class JsonUtil {

	private JsonUtil() {
	}

	// 파일을 읽어서 json 형태로 반환한다.
	public static Object getJsonObject(String userId) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(FileUtils.readFileToString(loadJsonWithSpringInternalClass(userId),
				StandardCharsets.UTF_8));
			return obj;
		} catch (Exception e) {
			return null;
		}
	}

	private static File loadJsonWithSpringInternalClass(String userId) throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:/json/" + userId + ".json");
	}
}
