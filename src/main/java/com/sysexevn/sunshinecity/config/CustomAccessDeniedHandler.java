package com.sysexevn.sunshinecity.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.dto.ResponseDto;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {
		logger.error("Message - {}", e.getMessage());
		ResponseDto<Void> responseResult = new ResponseDto<Void>();
		responseResult.setCode(HttpStatus.FORBIDDEN.value());
		responseResult.setMessage(e.getMessage());
		String json = new ObjectMapper().writeValueAsString(responseResult);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(json);
		response.flushBuffer();
	}
}
