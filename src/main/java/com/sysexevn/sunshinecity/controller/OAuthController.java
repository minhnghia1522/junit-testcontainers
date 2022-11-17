package com.sysexevn.sunshinecity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.config.JwtTokenProvider;
import com.sysexevn.sunshinecity.dto.JwtResponse;
import com.sysexevn.sunshinecity.dto.LoginUserDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
	@Autowired
	public IEmployeeService service;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserPojo) throws AuthenticationException, Exception {
		// Tạo chuỗi authentication từ username và password
		Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(loginUserPojo.getEmail(), loginUserPojo.getPassWord()));
		// Set chuỗi authentication đó cho UserPrincipal
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication != null) {
			// Trả về chuỗi jwt(authentication string)
			String jwt = tokenProvider.generateToken(authentication);
			return ResponseEntity.ok(new JwtResponse(jwt));
		}
		return ResponseEntity.ok("fail");
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) throws AuthenticationException, Exception {
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		Map<String, Object> expectedMap = new HashMap<>(claims);
		String token = tokenProvider.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
