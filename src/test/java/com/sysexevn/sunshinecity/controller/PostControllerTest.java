package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.sysexevn.sunshinecity.dto.PostDTO;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static Integer autoId = 18;
	
	@DisplayName("Test-Add-Post-Controller")
	@Test
	@Order(1)
	public void testCreate() throws Exception {
		PostDTO dto = new PostDTO();
		dto.setPostName("post name test");
		dto.setPostDescription("post description test");
		dto.setTitle("post title test");
		MvcResult result = this.mockMvc.perform(post("/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));	
	}
	
	@DisplayName("Test-Update-Post-Controller")
	@Test
	@Order(2)
	public void testUpdate() throws Exception {
		PostDTO dto = new PostDTO();
		dto.setPostName("post name test update");
		dto.setPostDescription("post description test update");
		dto.setTitle("post title test update");
		MvcResult result = this.mockMvc.perform(put("/post/" + autoId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}
	
	@DisplayName("Test-Select-All-Controller")
	@Test
	@Order(3)
	public void testSelectAll() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getAllPost")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
					
	}
	
	@DisplayName("Test-Get-Post-By-Id-Controller")
	@Test
	@Order(4)
	public void testGetPost() throws Exception{
		MvcResult result = this.mockMvc.perform(get("/post/" + autoId)).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}
	
	@DisplayName("Test-Delete-Post-Controller")
	@Test
	@Order(4)
	public void testDeletePost() throws Exception{
		PostDTO dto = new PostDTO();
		dto.setPostName("post name test update");
		dto.setPostDescription("post description test update");
		dto.setTitle("post title test update");
		MvcResult result = this.mockMvc.perform(delete("/post/" + autoId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}
}
