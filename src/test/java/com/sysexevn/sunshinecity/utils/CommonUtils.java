package com.sysexevn.sunshinecity.utils;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CommonUtils {

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
