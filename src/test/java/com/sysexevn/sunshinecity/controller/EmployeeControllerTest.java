package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;
import com.sysexevn.sunshinecity.entity.Role;
import com.sysexevn.sunshinecity.service.IEmployeeRoleService;
import com.sysexevn.sunshinecity.service.IRoleService;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

//
//	@Autowired
//	private IEmployeeRoleService employeeRoleService;
//
//	@Autowired
//	private IRoleService roleService;
//
//	public void setupData() {
//		Role r = new Role();
//		r.setRoleId(1);
//		r.setRoleName("USER");
//		roleService.create(r);
//		Role r2 = new Role();
//		r2.setRoleId(2);
//		r2.setRoleName("ADMIN");
//		roleService.create(r2);
//		EmployeeRoleDto e = new EmployeeRoleDto();
//		e.setEmployeeId(1);
//		e.setRoleId(1);
//		employeeRoleService.create(e);
//	}
//
	@Test
	@Order(1)
	@WithMockUser(username = "dummy_name", password = "dummy_password", authorities = "ADMIN")
	public void testCreateUser_Success() throws Exception {
		EmployeeDto employee = EmployeeDto.builder()//
				.username("dummy_name")//
				.password("12345")//
				.position("Developer")//
				.phone("0423658975")//
				.build();
		mockMvc.perform(//
				post("/employee/create")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee)))
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.fullName").value(employee.getFullName()))
				.andExpect(jsonPath("$.position").value(employee.getPosition()))
				.andExpect(jsonPath("$.phone").value(employee.getPhone()));
	}

	@Test
	@WithMockUser(username = "dummy_name", password = "dummy_password", authorities = "USER")
	public void testCreateUser_Throw_Forbidden() throws Exception {
		mockMvc.perform(//
				post("/employee/create")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())//
				.andExpect(status().is4xxClientError());
	}
//
//	public void createUser() throws Exception {
//		EmployeeDto dto = new EmployeeDto();
//		dto.setFullName("ntduoc");
//		this.mockMvc.perform(post("/employee").accept(MediaType.APPLICATION_JSON)//
//				.contentType(MediaType.APPLICATION_JSON_VALUE)//
//				.content(asJsonString(dto)))//
//				.andDo(print())//
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	@Order(2)
//	@WithMockUser(username = "tan-duoc@system-exe.com.vn", password = "12345", authorities = "USER")
//	public void TestGetUser() throws Exception {
//		mockMvc.perform(get("/employee/{id}", 1)).andDo(print()).andExpect(status().isOk())
//				.andExpect(jsonPath("$.employeeId").value(1));
//	}
//
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/employee/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, World")));
//	}
}
