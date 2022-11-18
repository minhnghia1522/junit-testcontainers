package com.sysexevn.sunshinecity.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.dto.ResponseDto;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		logger.error("Responding with unautheorized error. Message - {}", e.getMessage());
		ResponseDto<Void> responseResult = new ResponseDto<Void>();
		responseResult.setCode(HttpStatus.UNAUTHORIZED.value());
		responseResult.setMessage("Responding with unautheorized error. Message: " + e.getMessage());
		String json = new ObjectMapper().writeValueAsString(responseResult);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(json);
		response.flushBuffer();
	}
}
