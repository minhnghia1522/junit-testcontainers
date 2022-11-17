package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.sysexevn.sunshinecity.dto.PostDTO;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private static Integer autoId = 1;

	@DisplayName("Test-Add-Post-Controller")
	@Test
	@Order(1)
	public void testCreate() throws Exception {
		PostDTO dto = new PostDTO();
		dto.setPostName("post name test");
		dto.setPostDescription("post description test");
		dto.setTitle("post title test");
		MvcResult result = this.mockMvc.perform(post("/post").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto))).andDo(print())
				.andExpect(status().isOk()).andReturn();
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
		MvcResult result = this.mockMvc
				.perform(put("/post/" + autoId).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto)))
				.andDo(print()).andExpect(status().isOk()).andReturn();
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
	public void testGetPost() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/post/" + autoId)).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Delete-Post-Controller")
	@Test
	@Order(4)
	public void testDeletePost() throws Exception {
		MvcResult result = this.mockMvc.perform(delete("/post/" + autoId)).andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Insert-All-Post-Controller")
	@Test
	@Order(5)
	public void testInsertAll() throws Exception {
		// fake data
		List<PostDTO> dtos = new ArrayList<>();
		PostDTO dto1 = new PostDTO();
		dto1.setPostName("post name test 1");
		dto1.setPostDescription("post description test 1");
		dto1.setTitle("post title test 1");
		dtos.add(dto1);
		PostDTO dto2 = new PostDTO();
		dto2.setPostName("post name test 2");
		dto2.setPostDescription("post description test 2");
		dto2.setTitle("post title test 2");
		dtos.add(dto2);
		MvcResult result = this.mockMvc
				.perform(post("/post/insertAll").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dtos)))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Delete-All-Post-Controller")
	@Test
	@Order(6)
	public void testDeleteAllPost() throws Exception {
		MvcResult result = this.mockMvc.perform(delete("/post/deleteAll")).andExpect(status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("success"));
	}

	@DisplayName("Test-Get-Post-By-Id-Controller-Fail")
	@Test
	@Order(7)
	public void testGetPostFail() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/post/" + 99999)).andDo(print()).andExpect(status().isBadRequest())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("not found"));
	}

	@DisplayName("Test-Delete-Post-Controller-Fail")
	@Test
	@Order(8)
	public void testDeletePostFail() throws Exception {
		MvcResult result = this.mockMvc.perform(delete("/post/" + 99999)).andExpect(status().isBadRequest())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("not found"));
	}

	@DisplayName("Test-Update-Post-Controller-fail")
	@Test
	@Order(9)
	public void testUpdateFail() throws Exception {
		PostDTO dto = new PostDTO();
		dto.setPostName("post name test update");
		dto.setPostDescription("post description test update");
		dto.setTitle("post title test update");
		MvcResult result = this.mockMvc
				.perform(put("/post/" + 99999).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto)))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("not found"));
	}

	@DisplayName("Test-Select-All-Controller-Fail")
	@Test
	@Order(10)
	public void testSelectAllFail() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getAllPost")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW.contains("empty"));
	}

	@DisplayName("Test-Upload-File-Excel")
	@Test
	@Order(11)
	public void testUploadFileExcel() throws Exception {
		MultipartFile file = new MockMultipartFile("file", "/static/data_test/data_test.xlsx",
				"application/vnd.ms-excel", new ClassPathResource("/static/data_test/data_test.xlsx").getInputStream());
		this.mockMvc.perform(multipart("/post/excel/upload").file((MockMultipartFile) file)).andExpect(status().isOk());
	}

	@DisplayName("Test-Upload-File-CSV")
	@Test
	@Order(12)
	public void testUploadFileCSV() throws Exception {
		MultipartFile file = new MockMultipartFile("file", "/static/data_test/data_test_csv.csv",
				"application/vnd.ms-excel",
				new ClassPathResource("/static/data_test/data_test_csv.csv").getInputStream());
		this.mockMvc.perform(multipart("/post/csv/upload").file((MockMultipartFile) file)).andExpect(status().isOk());
	}

	@DisplayName("Test-Upload-File-Excel-Fail")
	@Test
	@Order(13)
	public void testUploadFileExcelFail() throws Exception {
		MultipartFile file = new MockMultipartFile("file_adfsdfsadfsadfsad", "/static/data_test/data_test_fail.xlsx",
				"application/vnd.ms-excel",
				new ClassPathResource("/static/data_test/data_test_fail.xlsx").getInputStream());
		// when
		this.mockMvc.perform(multipart("/post/excel/upload").file((MockMultipartFile) file))
				.andExpect(status().isBadRequest());
	}

	@DisplayName("Test-Upload-File-CSV-Fail")
	@Test
	@Order(14)
	public void testUploadFileCSVFail() throws Exception {
		MultipartFile file = new MockMultipartFile("file_adfsdfsadfsadfsad", "/static/data_test/data_test_csv_fail.csv",
				"application/vnd.ms-excel",
				new ClassPathResource("/static/data_test/data_test_csv_fail.csv").getInputStream());
		this.mockMvc.perform(multipart("/post/csv/upload").file((MockMultipartFile) file))
				.andExpect(status().isBadRequest());
	}

	@DisplayName("Test-Upload-File-Excel-Empty-File-Fail")
	@Test
	@Order(15)
	public void testUploadFileExcelEmptyFail() throws Exception {
		MultipartFile file = new MockMultipartFile("file", "/static/data_test/data_test_fail.xlsx",
				"application/vnd.ms-excel",
				new ClassPathResource("/static/data_test/data_test_fail.xlsx").getInputStream());
		this.mockMvc.perform(multipart("/post/excel/upload").file((MockMultipartFile) file))
				.andExpect(status().isBadRequest());
	}

	@DisplayName("Test-Upload-File-CSV-Empty-File-Fail")
	@Test
	@Order(16)
	public void testUploadFileCSVEmptyFail() throws Exception {
		MultipartFile file = new MockMultipartFile("file", "/static/data_test/data_test_csv_fail.csv",
				"application/vnd.ms-excel",
				new ClassPathResource("/static/data_test/data_test_csv_fail.csv").getInputStream());
		this.mockMvc.perform(multipart("/post/csv/upload").file((MockMultipartFile) file))
				.andExpect(status().isBadRequest());
	}

}
