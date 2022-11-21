package com.sysexevn.sunshinecity.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CommonUtils<T> {

	public static ObjectMapper mapper = new ObjectMapper();

	public static String asJsonString(final Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T jsonToObject(String resultDOW) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(resultDOW, new TypeReference<T>() {
		});
	}
}
