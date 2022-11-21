package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.github.javafaker.Faker;
import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;
	private static Faker faker = new Faker();
	private static EmployeeDto employee1 = new EmployeeDto();
	private static EmployeeDto employee2 = new EmployeeDto();
	private static EmployeeDto employee3 = new EmployeeDto();

	@BeforeAll
	private static void setupData() {
		employee1 = EmployeeDto.builder()//
				.username(faker.name().firstName())//
				.fullName(faker.name().fullName()).password(faker.code().asin())//
				.birthday(faker.date().birthday()).position(faker.job().position())//
				.phone(faker.phoneNumber().subscriberNumber(10))//
				.department(faker.company().suffix()).build();
		employee2 = EmployeeDto.builder()//
				.username(faker.name().firstName())//
				.fullName(faker.name().fullName()).password(faker.code().asin())//
				.birthday(faker.date().birthday()).position(faker.job().position())//
				.phone(faker.phoneNumber().subscriberNumber(10))//
				.department(faker.company().suffix()).build();
		employee3 = EmployeeDto.builder()//
				.username(faker.name().firstName())//
				.fullName(faker.name().fullName()).password(faker.code().asin())//
				.birthday(faker.date().birthday()).position(faker.job().position())//
				.phone(faker.phoneNumber().subscriberNumber(10))//
				.department(faker.company().suffix()).build();
	}

	@Test
	@Order(1)
	@WithMockUser(username = "dummy_name", password = "dummy_password", authorities = "ADMIN")
	public void testCreateUser() throws Exception {
		MvcResult result = mockMvc.perform(//
				post("/employee/create")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee1)))
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.fullName").value(employee1.getFullName()))
				.andExpect(jsonPath("$.position").value(employee1.getPosition()))
				.andExpect(jsonPath("$.phone").value(employee1.getPhone())).andReturn();
		employee1.setId(Integer.decode(new JSONObject(result.getResponse().getContentAsString())//
				.getString("id")));
	}

	@Test
	@Order(2)
	@WithMockUser(username = "dummy_name", password = "dummy_password", authorities = "USER")
	public void testCreateUser_Fail() throws Exception {
		mockMvc.perform(//
				post("/employee/create")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee1)))
				.andDo(print())//
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(3)
	@WithMockUser(authorities = "USER")
	public void testGetUser() throws Exception {
		mockMvc.perform(//
				get("/employee/{id}", employee1.getId()))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(employee1.getId()))//
				.andExpect(jsonPath("$.username").value(employee1.getUsername()))//
				.andExpect(jsonPath("$.phone").value(employee1.getPhone()));
	}

	@Test
	@Order(4)
	@WithMockUser(authorities = "USER")
	public void testGetUser_Fail() throws Exception {
		mockMvc.perform(//
				get("/employee/{id}", 9))//
				.andDo(print())//
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(5)
	@WithMockUser(authorities = "USER")
	public void testUpdate() throws Exception {
		employee1.setPhone("565656565");
		mockMvc.perform(//
				put("/employee/update")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee1)))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(employee1.getId()))//
				.andExpect(jsonPath("$.username").value(employee1.getUsername()))//
				.andExpect(jsonPath("$.phone").value(employee1.getPhone()));
	}

	@Test
	@Order(6)
	@WithMockUser(authorities = "USER")
	public void testUpdate_Fail() throws Exception {
		EmployeeDto employee = new EmployeeDto();
		employee.setId(9);
		employee.setUsername("hoangnhat");
		mockMvc.perform(//
				put("/employee/update")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee)))//
				.andDo(print())//
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(7)
	@WithMockUser(authorities = "ADMIN")
	public void testGetAll() throws Exception {
		mockMvc.perform(//
				get("/employee/getAll"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.size()").value(2));
	}

	@Test
	@Order(8)
	@WithMockUser(authorities = "ADMIN")
	public void testDelete() throws Exception {
		mockMvc.perform(//
				delete("/employee/delete")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee1)))//
				.andDo(print())//
				.andExpect(status().isOk());

		mockMvc.perform(//
				get("/employee/getAll"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.size()").value(1));
	}

	@Test
	@Order(9)
	@WithMockUser(authorities = "ADMIN")
	public void testDelete_Fail() throws Exception {
		EmployeeDto employee = new EmployeeDto();
		employee.setId(19);
		employee.setUsername("toan");
		mockMvc.perform(//
				delete("/employee/delete")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employee)))//
				.andDo(print())//
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(10)
	@WithMockUser(authorities = "ADMIN")
	public void testCreateListUser() throws Exception {
		List<EmployeeDto> employeeDtoes = List.of(employee2, employee3);
		mockMvc.perform(//
				post("/employee/create-users")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON_VALUE)//
						.content(asJsonString(employeeDtoes)))
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$.[0].username").value(employee2.getUsername()))
				.andExpect(jsonPath("$.[1].username").value(employee3.getUsername()));
		mockMvc.perform(//
				get("/employee/getAll"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.size()").value(3));
	}
}
