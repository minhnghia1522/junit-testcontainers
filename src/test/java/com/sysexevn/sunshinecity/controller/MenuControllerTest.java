package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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

	private static Faker faker = new Faker();
	private static MenuDto menuDto1 = new MenuDto();
	private static MenuDto menuDto2 = new MenuDto();
	private static MenuDto menuDto3 = new MenuDto();

	@BeforeAll
	public static void setup() {
		menuDto1 = MenuDto.builder().menuName(faker.name().firstName()).menuPath(faker.internet().emailAddress())
				.createdAt(faker.date().birthday()).updateAt(faker.date().birthday()).build();

		menuDto2 = MenuDto.builder().menuName(faker.name().firstName()).menuPath(faker.internet().emailAddress())
				.createdAt(faker.date().birthday()).updateAt(faker.date().birthday()).build();

		menuDto3 = MenuDto.builder().menuName(faker.name().firstName()).menuPath(faker.internet().emailAddress())
				.createdAt(faker.date().birthday()).updateAt(faker.date().birthday()).build();
	}

	@DisplayName("Test-Save-All-Menu")
	@Test
	@Order(1)
	public void postSaveAllMenu() throws Exception {
		List<MenuDto> listDto = Arrays.asList(menuDto1, menuDto2);
		String result = this.mockMvc.perform(post("/menu/CreatAll").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(listDto)))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.size()").value(2)).andReturn().getResponse().getContentAsString();
		menuDto1.setId(Integer.decode(new JSONArray(result).getJSONObject(0).getString("id")));
		menuDto2.setId(Integer.decode(new JSONArray(result).getJSONObject(1).getString("id")));
	}

	@DisplayName("Test-Get-Menu-By-ID")
	@Test
	@Order(2)
	public void getMenuById() throws Exception {
		this.mockMvc.perform(get("/menu/{id}", 1))//
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.menuName").value(menuDto1.getMenuName()));
		this.mockMvc.perform(get("/menu/{id}", 2))//
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.menuName").value(menuDto2.getMenuName()));
	}

	@DisplayName("Test-Update-Menu")
	@Test
	@Order(3)
	public void UpdateMenu() throws Exception {
		menuDto1.setMenuName("co111");
		menuDto1.setUpdateAt(new Date());
		this.mockMvc.perform(put("/menu/updatemenu").accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(menuDto1)))//
				.andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(menuDto1.getId()))
				.andExpect(jsonPath("$.menuName").value(menuDto1.getMenuName())).andReturn();
	}

	@DisplayName("Test-Get-All-Menu")
	@Test
	@Order(4)
	public void getAllMenu() throws Exception {
		this.mockMvc.perform(get("/menu/getAll")).andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2));
	}

	@DisplayName("Test-Delete-Menu")
	@Test
	@Order(5)
	public void deleteMenu() throws Exception {
		int id = 1;
		this.mockMvc.perform(delete("/menu/delete/{id}", id)).andDo(print())//
				.andExpect(status().isOk());

		this.mockMvc.perform(get("/menu/getAll")).andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(1));

	}

	@DisplayName("Test-Get-Menu-By-Id-Is-Null")
	@Test
	@Order(6)
	public void getByIdIsNull() throws Exception {// bad request 400
		this.mockMvc.perform(get("/menu/1")).andExpect(status().is4xxClientError());
	}

	@DisplayName("Test-Create-Menu")
	@Test
	@Order(7)
	public void createMenu() throws Exception {
		this.mockMvc.perform(post("/menu/addmenu")//
				.accept(MediaType.APPLICATION_JSON)//
				.contentType(MediaType.APPLICATION_JSON_VALUE)//
				.content(asJsonString(menuDto3))).andExpect(status().isOk())//
				.andExpect(jsonPath("$.menuName").value(menuDto3.getMenuName()));

		this.mockMvc.perform(get("/menu/getAll")).andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2));
	}

}
