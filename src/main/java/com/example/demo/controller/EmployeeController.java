package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {
		EmployeeDto employee = service.getById(id);
		if(employee == null) {
			return (ResponseEntity<?>) ResponseEntity.status(404);
		}
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/employees/")
	public ResponseEntity<?> getAll() {
		List<EmployeeDto> employees = service.getAll();
		return ResponseEntity.ok(employees);
	}
}
