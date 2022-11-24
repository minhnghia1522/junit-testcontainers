package com.sysexevn.sunshinecity.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;
import com.sysexevn.sunshinecity.converter.EmployeeConverter;
import com.sysexevn.sunshinecity.dao.IEmployeeDao;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.entity.Employee;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	public EmployeeServiceImpl service;

	@Mock
	private IEmployeeDao employeeDao;

	@Spy
	private EmployeeConverter converter = Mappers.getMapper(EmployeeConverter.class);

	@Mock
	private PasswordEncoder encoder;

	private Employee employee;

	private EmployeeDto employeeDto;

	private String password;

	@BeforeEach
	public void setUp() {
		employee = new Employee();
		employee.setUsername("hoang");
		employee.setFullName("hoang nhat");
		employee.setPassword("12345");

		employeeDto = new EmployeeDto();
		employeeDto.setUsername("hoang");
		employeeDto.setFullName("hoang nhat");
		employeeDto.setPassword("12345");

		lenient().when(converter.convert(employeeDto)).thenReturn(employee);
		lenient().when(converter.convert(employee)).thenReturn(employeeDto);
		lenient().when(converter.convertListToEntity(List.of(employeeDto))).thenReturn(List.of(employee));
		lenient().when(converter.convertListToDto(List.of(employee))).thenReturn(List.of(employeeDto));
	}

	@DisplayName("Test-Create-Employee")
	@Test
	public void testCreate() {
		// given - precondition or setup
		lenient().when(employeeDao.insert(any(Employee.class))).thenReturn(new Result<Employee>(1, employee));

		lenient().when(encoder.encode(employee.getPassword())).thenReturn(password);

		// when - action or the behavior that we are going test
		EmployeeDto result = service.createEmployee(employeeDto);

		// then - verify the output
		assertNotNull(result);
		assertEquals(employee.getFullName(), result.getFullName());

	}

	@DisplayName("Test-Get-By-Id")
	@Test
	public void testGetById() {
		when(employeeDao.findById(anyInt())).thenReturn(Optional.of(employee));
		EmployeeDto result = service.getById(1);
		assertNotNull(result);
		assertEquals(employee.getFullName(), result.getFullName());
	}

	@DisplayName("Test-Get-By-User-Name")
	@Test
	public void testGetByUserName() {
		when(employeeDao.findByUsername(anyString())).thenReturn(Optional.of(employee));
		EmployeeDto result = service.getByUsername("toan");
		assertNotNull(result);
		assertEquals(employee.getFullName(), result.getFullName());
	}

	@DisplayName("Test-Get-All")
	@Test
	public void testGetAll() {
		when(employeeDao.findAllEmployee()).thenReturn(List.of(employee));
		List<EmployeeDto> result = service.getAll();
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@DisplayName("Test-Save-All")
	@Test
	public void testSaveAll() {
		when(employeeDao.insertAll(anyList()))//
				.thenReturn(new BatchResult<Employee>(new int[1], List.of(employee)));
		List<EmployeeDto> result = service.saveAll(List.of(employeeDto));
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@DisplayName("Test-Get-By-Id-Fail")
	@Test
	public void testGetById_Fail() {
		when(employeeDao.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> service.getById(15));
	}

	@DisplayName("Test-Update")
	@Test
	public void testUpdate() {
		when(employeeDao.update(any(Employee.class))).thenReturn(new Result<Employee>(1, employee));
		EmployeeDto result = service.update(employeeDto);
		assertNotNull(result);
		assertEquals(employee.getFullName(), result.getFullName());
	}

	@DisplayName("Test-Update-Fail")
	@Test
	public void testUpdate_Fail() {
		when(employeeDao.update(any(Employee.class))).thenReturn(new Result<Employee>(0, employee));
		assertThrows(NotFoundException.class, () -> service.update(employeeDto));
	}

	@DisplayName("Test-Delete")
	@Test
	public void testDelete() {
		when(employeeDao.delete(any(Employee.class))).thenReturn(new Result<Employee>(1, employee));
		EmployeeDto result = service.delete(employeeDto);
		assertNotNull(service.delete(employeeDto));
		assertEquals(employee.getFullName(), result.getFullName());
	}

	@DisplayName("Test-Delete-Fail")
	@Test
	public void testDelete_Fail() {
		when(employeeDao.delete(any(Employee.class))).thenReturn(new Result<Employee>(0, employee));
		assertThrows(NotFoundException.class, () -> service.delete(employeeDto));
	}
}
