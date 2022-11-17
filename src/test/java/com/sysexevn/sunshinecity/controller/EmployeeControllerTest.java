package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.domain.Role;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;
import com.sysexevn.sunshinecity.service.IEmployeeRoleService;
import com.sysexevn.sunshinecity.service.IRoleService;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private IEmployeeRoleService employeeRoleService;

	@Autowired
	private IRoleService roleService;

	public void setupData() {
		Role r = new Role();
		r.setRoleId(1);
		r.setRoleName("USER");
		roleService.create(r);
		Role r2 = new Role();
		r2.setRoleId(2);
		r2.setRoleName("ADMIN");
		roleService.create(r2);
		EmployeeRoleDto e = new EmployeeRoleDto();
		e.setEmployeeId(1);
		e.setRoleId(1);
		employeeRoleService.create(e);
	}

	@Test
	@Order(1)
	@WithMockUser(username = "tan-duoc@system-exe.com.vn", password = "12345", authorities = "ADMIN")
	public void TestCreateUser() throws Exception {
		this.setupData();
		EmployeeDto employee = new EmployeeDto();
		employee.setFullName("Tan Duoc");
		employee.setEmail("tan-duoc@system-exe.com.vn");
		employee.setBirthday(new Date());
		employee.setDepartment("Offshore");
		employee.setPosition("Developer");
		employee.setPhone("0423658975");
		employee.setPassWord("12345");
		mockMvc.perform(post("/employee/create").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(employee))).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(1));
	}

	@Test
	@Order(2)
	@WithMockUser(username = "tan-duoc@system-exe.com.vn", password = "12345", authorities = "USER")
	public void TestGetUser() throws Exception {
		mockMvc.perform(get("/employee/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(1));
	}

}
