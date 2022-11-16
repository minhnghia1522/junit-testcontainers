package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;

@TestMethodOrder(OrderAnnotation.class)
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
		dto.setCreatedAt(null);
		this.mockMvc.perform(post("/menu/addmenu")
				.accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(dto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.menuId").value(1)) 
				.andExpect(jsonPath("$.menuName").value(dto.getMenuName())) 
				.andDo(print())
				.andReturn().getResponse().getContentAsString();

	}

	@DisplayName("Test-Get-Menu-By-ID")
	@Test
	@Order(2)
	public void getMenuById() throws Exception {

		MenuDto dto = new MenuDto();
		dto.setMenuName("co111");
		MvcResult result = this.mockMvc.perform(get("/menu/" + 1)).andDo(print())
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.*", hasSize(5))).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));

	}

	@DisplayName("Test-Get-All-Menu")
	@Test
	@Order(3)
	public void getAllMenu() throws Exception {
		MvcResult result = this.mockMvc
				.perform(get("/menu/getAll"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn();

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

	@DisplayName("Test-Get-Menu-By-Id-Is-Null")
	@Test
	@Order(6)
	public void getByIdIsNull() throws Exception {
		this.mockMvc.perform(get("/menu/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

	@DisplayName("Test-Save-All-Menu")
	@Test
	@Order(7)
	public void postSaveAllMenu() throws Exception {
		Menu dto = new Menu();
		dto.setMenuId(1);
		dto.setMenuName("co111");
		dto.setUpdateAt(new Date());
		List<Menu> listDto = new ArrayList<Menu>();
		listDto.add(dto);
		this.mockMvc.perform(post("/menu/CreatAll").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(listDto)))//
				.andDo(print())//
				.andExpect(status().isOk()).andReturn();
	}

}
