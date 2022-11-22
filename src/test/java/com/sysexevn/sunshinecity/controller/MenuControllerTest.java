package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.github.javafaker.Faker;
import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.MenuDto;

import lombok.extern.slf4j.Slf4j;

@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@WithMockUser(authorities = { "USER" })
@Slf4j
public class MenuControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

	private Faker faker = new Faker();

	private MenuDto menuDto1 = MenuDto.builder().menuName(faker.name().firstName())
			.menuPath(faker.internet().emailAddress()).createdAt(faker.date().birthday())
			.updateAt(faker.date().birthday()).build();

	private MenuDto menuDto2 = MenuDto.builder().menuName(faker.name().firstName())
			.menuPath(faker.internet().emailAddress()).createdAt(faker.date().birthday())
			.updateAt(faker.date().birthday()).build();

	@DisplayName("Test-Save-All-Menu")
	@Test
	@Order(1)
	public void postSaveAllMenu() throws Exception {
		List<MenuDto> listDto = Arrays.asList(menuDto1, menuDto2);
		MvcResult mvcResult =  this.mockMvc.perform(post("/menu/CreatAll").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(listDto)))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(2))).andReturn();
//				.andExpect(jsonPath("$.*", hasSize(2)));
	}

	@DisplayName("Test-Get-Menu-By-ID")
	@Test
	@Order(2)
	public void getMenuById() throws Exception {
		this.mockMvc.perform(get("/menu/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.menuName").value(menuDto1.getMenuName()));
	}

//	@DisplayName("Test-Update-Menu")
//	@Test
//	@Order(3)
//	public void UpdateMenu() throws Exception {
//		MenuDto dto = new MenuDto();
//		dto.setMenuId(4);
//		dto.setMenuName("co111");
//		dto.setUpdateAt(new Date());
//		MvcResult result = this.mockMvc.perform(put("/menu/updatemenu").accept(MediaType.APPLICATION_JSON)//
//				.contentType(MediaType.APPLICATION_JSON_VALUE)//
//				.content(asJsonString(dto)))//
//				.andDo(print())//
//				.andExpect(status().isOk()).andExpect(jsonPath("$.menuId").value(dto.getMenuId()))
//				.andExpect(jsonPath("$.menuName").value(dto.getMenuName())).andReturn();
//		String resultDOW = result.getResponse().getContentAsString();
//		assertNotNull(resultDOW.contains("success"));
//	}
//
//	@DisplayName("Test-Get-All-Menu")
//	@Test
//	@Order(4)
//	public void getAllMenu() throws Exception {
//		this.mockMvc.perform(get("/menu/getAll")).andDo(print()).andExpect(status().isOk())
//				.andExpect(jsonPath("$.*", hasSize(3))).andReturn().getResponse().getContentAsString();
//	}
//
//	@DisplayName("Test-Delete-Menu")
//	@Test
//	@Order(5)
//	public void deleteMenu() throws Exception {
//		int id = 1;
//		this.mockMvc.perform(delete("/menu/delete/{id}", id)).andDo(print())//
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//	}
//
//	@DisplayName("Test-Get-Menu-By-Id-Is-Null")
//	@Test
//	@Order(6)
//	public void getByIdIsNull() throws Exception {// bad request 400
//		this.mockMvc.perform(get("/menu/1")).andExpect(status().isBadRequest()).andReturn().getResponse()
//				.getContentAsString();
//	}
//
//	@DisplayName("Test-Create-Menu")
//	@Test
//	@Order(7)
//	public void createMenu() throws Exception {
//		MenuDto dto = new MenuDto();
//		dto.setMenuName("co111");
//		dto.setCreatedAt(null);
//		this.mockMvc.perform(post("/menu/addmenu").accept(MediaType.APPLICATION_JSON)//
//				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto))).andExpect(status().isOk())
//				.andExpect(jsonPath("$.menuId").value(1)).andExpect(jsonPath("$.menuName").value(dto.getMenuName()))
//				.andDo(print()).andReturn().getResponse().getContentAsString();
//
//	}

}
