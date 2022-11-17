package com.sysexevn.sunshinecity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public ResponseEntity<?> home() {
		return ResponseEntity.ok("Login successfully!!! &#128526;");
	}

	@GetMapping("/getInfo")
	public ResponseEntity<?> getInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(principal);
	}
}
