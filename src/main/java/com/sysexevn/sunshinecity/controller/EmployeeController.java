package com.sysexevn.sunshinecity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	public IEmployeeService service;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
		EmployeeDto result = service.createEmployee(dto);
		return ResponseEntity.ok(result);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/create-users")
	public ResponseEntity<List<EmployeeDto>> createAll(@RequestBody List<EmployeeDto> dtoList) {
		List<EmployeeDto> result = service.saveAll(dtoList);
		return ResponseEntity.ok(result);
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable("id") int id) throws NotFoundException {
		EmployeeDto employee = service.getById(id);
		if (employee == null) {
			throw new NotFoundException();
		}
		return ResponseEntity.ok(employee);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAll() {
		List<EmployeeDto> employees = service.getAll();
		return ResponseEntity.ok(employees);
	}

	@PutMapping("/update")
	public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = service.getById(employeeDto.getId());
		EmployeeDto employeeResult = null;
		if (employee != null) {
			employeeResult = service.update(employeeDto);
		}
		return ResponseEntity.ok(employeeResult);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<EmployeeDto> delete(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = service.getById(employeeDto.getId());
		EmployeeDto employeeResult = null;
		if (employee != null) {
			employeeResult = service.delete(employeeDto);
		}
		return ResponseEntity.ok(employeeResult);
	}
}
