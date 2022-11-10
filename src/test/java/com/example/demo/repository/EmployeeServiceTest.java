package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.demo.AbstractPostgreSQLContainer;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest extends AbstractPostgreSQLContainer {

	@Autowired
	EmployeeService service;

	@DisplayName("Test-Create-Employee")
	@Test
	@Order(1)
	public void testCreate() {
		Employee employee = new Employee();
		employee.setEmployeeId(222);
		employee.setFullName("Tan Duoc");
		employee.setEmail("tan-duoc@system-exe.com.vn");
		employee.setBirthday(new Date());
		employee.setDepartment("Offshore");
		employee.setPosition("Developer");
		employee.setPhone("0423658975");
		int result = service.createEmployeee(employee);
		assertEquals(1, result);
		
	}

	@DisplayName("Test-Get-By-Id")
	@Test
	@Order(2)
	public void testGetById() {
		Employee employee = new Employee();
		employee.setEmployeeId(222);
		employee.setFullName("Tan Duoc");
		employee.setEmail("tan-duoc@system-exe.com.vn");
		employee.setBirthday(new Date());
		employee.setDepartment("Offshore");
		employee.setPosition("Developer");
		employee.setPhone("0423658975");

		EmployeeDto dto = service.getById(employee.getEmployeeId());

		assertEquals(employee.getFullName(), dto.fullName);
	}

	@DisplayName("Test-Get-List")
	@Test
	@Order(3)
	public void testGetList() {
		Employee employee = new Employee();
		employee.setEmployeeId(333);
		employee.setFullName("Hoang Anh");
		service.createEmployeee(employee);

		List<EmployeeDto> listDtoes = service.getAll();
		assertEquals(2, listDtoes.size());
	}

	@DisplayName("Test-Get-Id-Not-Found")
	@Test
	@Order(4)
	public void testGetIdNotFound() {
		assertThrows(NoSuchElementException.class, () -> service.getById(55));
	}

}
