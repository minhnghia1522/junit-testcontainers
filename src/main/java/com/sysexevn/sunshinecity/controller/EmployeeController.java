package com.sysexevn.sunshinecity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	public IEmployeeService service;

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") int id) {
		EmployeeDto employee = service.getById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAll() {
		List<EmployeeDto> employees = service.getAll();
		return ResponseEntity.ok(employees);
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
		EmployeeDto employee = service.createEmployeee(dto);
		return ResponseEntity.ok(employee);
	}

}
