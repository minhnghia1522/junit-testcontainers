package com.sysexevn.sunshinecity.controller;

import static com.sysexevn.sunshinecity.utils.CommonUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import com.sysexevn.sunshinecity.dto.ProductDto;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(authorities = { "USER" })
public class ProductControllerTest extends AbsTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void create() throws Exception {
		ProductDto dto = ProductDto.builder().title("Bún Đậu")
				.picture("https://www.thatlangon.com/wp-content/uploads/2020/06/bun-dau-7-e1593236905415.jpg")
				.description(
						"Bún đậu mắm tôm là món ăn đơn giản, dân dã trong ẩm thực miền Bắc Việt Nam. Đây là món thường được dùng như bữa ăn nhẹ, ăn chơi. Thành phần chính gồm có bún tươi, đậu hũ chiên vàng, chả cốm, nem chua, dồi chó, mắm tôm pha chanh, ớt và ăn kèm với các loại rau thơm như tía tô, kinh giới, rau húng, xà lách, cà pháo...")
				.oldPrice(BigDecimal.valueOf(65000)).newPrice(BigDecimal.valueOf(80000)).shopName("Minh Nghia").build();

		this.mockMvc
				.perform(post("/product").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto)))
				.andDo(print())//
				.andExpect(status().isOk());

	}

	@Test
	@Order(2)
	public void getById() throws Exception {
		this.mockMvc.perform(get("/product/" + 1)).andDo(print()).andExpect(status().isOk())//
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@Order(2)
	public void getList() throws Exception {
		this.mockMvc.perform(get("/product/get-list").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void update() throws Exception {
		ProductDto dto = ProductDto.builder().id(1).title("Bun Bo Hue").build();

		this.mockMvc
				.perform(patch("/product").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto)))
				.andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.title", is("Bun Bo Hue")));
	}

	@Test
	@Order(2)
	public void updateException() throws Exception {
		ProductDto dto = ProductDto.builder().id(2).title("Bun Bo Hue").build();

		this.mockMvc
				.perform(patch("/product").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(dto)))
				.andDo(print())//
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(3)
	public void remove() throws Exception {
		this.mockMvc.perform(delete("/product").queryParam("id", "1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	@Order(3)
	public void removeIdNotExist() throws Exception {
		this.mockMvc.perform(delete("/product").queryParam("id", "2").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

	}

}
