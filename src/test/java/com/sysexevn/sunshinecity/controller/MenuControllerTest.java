package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.MenuDto;

@AutoConfigureMockMvc
public class MenuControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("Test-Create-Menu")
	@Test
	@Order(1)
	public void createMenu() throws Exception {
		MenuDto dto = new MenuDto();
		dto.setMenuName("co111");
		MvcResult result = this.mockMvc.perform(post("/menu/addmenu").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(dto)))//
				.andDo(print())//
				.andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Get-Menu-By-ID")
	@Test
	@Order(2)
	public void getMenuById() throws Exception {
		MenuDto dto = new MenuDto();
		dto.setMenuName("co111");
		MvcResult result = this.mockMvc.perform(get("/menu/" + 1).content(asJsonString(dto))).andDo(print())
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.menuName", is(dto.getMenuName()))).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Get-All-Menu")
	@Test
	@Order(3)
	public void getAllMenu() throws Exception {
		MenuDto dto = new MenuDto();
		dto.setMenuName("co111");
		MvcResult result = this.mockMvc.perform(get("/menu/getAll").content(asJsonString(dto))).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));

	}

	@DisplayName("Test-Update-Menu")
	@Test
	@Order(4)
	public void UpdateMenu() throws Exception {
		MenuDto dto = new MenuDto();
		dto.setMenuId(1);
		dto.setMenuName("co111");
		dto.setUpdateAt(new Date());
		MvcResult result = this.mockMvc.perform(put("/menu/updatemenu").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(dto)))//
				.andDo(print())//
				.andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Delete-Menu")
	@Test
	@Order(5)
	public void deleteMenu() throws Exception {
		MenuDto dto = new MenuDto();
		dto.setMenuId(1);
		dto.setMenuName("co111");
		dto.setUpdateAt(new Date());
		MvcResult result = this.mockMvc.perform(delete("/menu/delete").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(dto)))//
				.andDo(print())//
				.andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/menu/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}
}
