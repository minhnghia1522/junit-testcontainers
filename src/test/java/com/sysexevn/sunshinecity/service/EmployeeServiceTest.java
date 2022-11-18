package com.sysexevn.sunshinecity.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.converter.EmployeeConverter;
import com.sysexevn.sunshinecity.dao.IEmployeeDao;
import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	public EmployeeServiceImpl service;

	@Mock
	private IEmployeeDao employeeDao;

	@Mock
	private EmployeeConverter converter;

	private Employee employee;

	private EmployeeDto employeeDto;

	@BeforeEach
	public void setUp() {
		employee = mock(Employee.class);
		employeeDto = mock(EmployeeDto.class);
	}
	
	@DisplayName("Test-Create-Employee")
	@Test
	@Order(1)
	public void testCreate() {
		// given - precondition or setup
		lenient().when(employeeDao.insert(employee)).thenReturn(new Result<Employee>(1, employee));
		lenient().when(converter.convert(employeeDto)).thenReturn(employee);
		lenient().when(converter.convert(employee)).thenReturn(employeeDto);

		// when - action or the behaviour that we are going test
		EmployeeDto savedEmployee = service.createEmployee(employeeDto);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();

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
		service.createEmployee(employee);

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
