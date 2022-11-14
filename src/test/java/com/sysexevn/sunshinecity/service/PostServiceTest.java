package com.sysexevn.sunshinecity.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.dto.PostDTO;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostServiceTest extends AbsTest {

	@Autowired
	IPostService postService;

	@DisplayName("Test-Create-Post")
	@Test
	@Order(1)
	public void testCreate() {
		PostDTO post = new PostDTO();
		post.setId(1);
		post.setTitle("test tiêu đề post");
		post.setPostName("test tên post");
		post.setPostDescription("test mô tả post");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		int result = postService.createPost(post);
		assertEquals(1, result);
	}
	
	@DisplayName("Test-Update-Post")
	@Test
	@Order(2)
	public void testUpdate() {
		PostDTO post = new PostDTO();
		post.setId(1);
		post.setTitle("test tiêu đề post update");
		post.setPostName("test tên post update");
		post.setPostDescription("test mô tả post update");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		int result = postService.updatePost(post);
		assertEquals(1, result);
	}
	
	@DisplayName("Test-Select-All-Post")
	@Test
	@Order(3)
	public void testSelectAll() {
		PostDTO post = new PostDTO();
		post.setId(2);
		post.setTitle("test tiêu đề post 2");
		post.setPostName("test tên post 2");
		post.setPostDescription("test mô tả post 2");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		postService.createPost(post);
		List<PostDTO> listPost = postService.getAll();
		assertEquals(2, listPost.size());
	}
	
	@DisplayName("Test-Select-By-Id-Post")
	@Test
	@Order(4)
	public void testSelectId() {
		PostDTO post = postService.getById(2);
		assertEquals("test tiêu đề post 2", post.getTitle());
	}
	
	@DisplayName("Test-Delete-Post")
	@Test
	@Order(5)
	public void testDelete() {
		assertDoesNotThrow(() -> postService.deleteAllPost());
	}
	
}
