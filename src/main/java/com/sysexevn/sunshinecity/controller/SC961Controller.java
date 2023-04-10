package com.sysexevn.sunshinecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.dto.SC961Filter;
import com.sysexevn.sunshinecity.service.ISC961Service;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("sc961")
@Slf4j
public class SC961Controller {

	@Autowired
	ISC961Service service;
	
	@PostMapping("/search")
	public ResponseEntity<?> getById(@RequestBody SC961Filter request) {
		log.info("ok", request.toString());
		return ResponseEntity.ok(service.search(request));
	}
}
