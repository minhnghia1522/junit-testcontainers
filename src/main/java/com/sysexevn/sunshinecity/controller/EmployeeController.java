package com.sysexevn.sunshinecity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.config.JwtAuthenticationFilter;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	public IEmployeeService service;

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") int id) {
		EmployeeDto employee = service.getById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		
		log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return ResponseEntity.ok(employee);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAll() {
		List<EmployeeDto> employees = service.getAll();
		return ResponseEntity.ok(employees);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Integer> create(@RequestBody EmployeeDto dto) {
		Integer tt = service.createEmployeee(dto);
		return ResponseEntity.ok(tt);
	}

}
