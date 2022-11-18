package com.sysexevn.sunshinecity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import com.sysexevn.sunshinecity.dto.SignUpDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	public IEmployeeService service;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) throws AuthenticationException, Exception {
		Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication != null) {
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

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody SignUpDto signUpDto) {
		service.signup(signUpDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
