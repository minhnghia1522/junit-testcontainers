package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void createUser() throws Exception {
		EmployeeDto dto = new EmployeeDto();
		dto.setFullName("ntduoc");
		this.mockMvc.perform(post("/employee").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(dto)))//
				.andDo(print())//
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void getUserById() throws Exception {
		EmployeeDto dto = new EmployeeDto();
		dto.setFullName("ntduoc");
		this.mockMvc.perform(get("/employee/" + 1).content(asJsonString(dto))).andDo(print()).andExpect(status().isOk())//
				.andExpect(jsonPath("$.fullName", is(dto.getFullName())));
	}

	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/employee/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}
}
