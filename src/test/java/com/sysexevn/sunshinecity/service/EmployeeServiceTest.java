package com.sysexevn.sunshinecity.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeServiceTest {

	@Autowired
	public IEmployeeService service;

//	@BeforeAll
//	public void resetData() {
//		this.resetDatabase();
//	}

	@DisplayName("Test-Create-Employee")
//	@Test
	@Order(1)
	public void testCreate() {
		EmployeeDto employee = new EmployeeDto();
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
//	@Test
	@Order(2)
	public void testGetById() {
		Employee employee = new Employee();
		employee.setFullName("Tan Duoc");
		employee.setEmail("tan-duoc@system-exe.com.vn");
		employee.setBirthday(new Date());
		employee.setDepartment("Offshore");
		employee.setPosition("Developer");
		employee.setPhone("0423658975");

		EmployeeDto dto = service.getById(1);

		assertEquals(employee.getFullName(), dto.getFullName());
	}

	@DisplayName("Test-Get-List")
//	@Test
	@Order(3)
	public void testGetList() {
		EmployeeDto employee = new EmployeeDto();
		employee.setFullName("Hoang Anh");
		service.createEmployeee(employee);

		List<EmployeeDto> listDtoes = service.getAll();
		assertEquals(2, listDtoes.size());
	}

	@DisplayName("Test-Get-Id-Not-Found")
//	@Test
	@Order(4)
	public void testGetIdNotFound() {
		assertThrows(NotFoundException.class, () -> service.getById(55));
	}

}
