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

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
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
	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") int id) throws NotFoundException {
		EmployeeDto employee = service.getById(id);
		if (employee == null) {
			throw new NotFoundException();
		}

		log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return ResponseEntity.ok(employee);
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAll() {
		List<EmployeeDto> employees = service.getAll();
		return ResponseEntity.ok(employees);
	}

	@PreAuthorize("hasAuthority('CHECKER')")
	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
		EmployeeDto result = service.createEmployee(dto);
		return ResponseEntity.ok(result);
	}

}
