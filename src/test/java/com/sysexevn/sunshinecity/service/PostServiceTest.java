package com.sysexevn.sunshinecity.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.converter.PostConverter;
import com.sysexevn.sunshinecity.dao.IPostDAO;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;
import com.sysexevn.sunshinecity.service.impl.PostServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

	@InjectMocks
	private PostServiceImpl postService;

	@Mock
	private IPostDAO postDAO;

	@Mock
	private PostConverter converter;

	@Before
	public void setUpAll() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	Post post;
	PostDTO postDTO;

	@BeforeEach
	public void setUp() {
		post = mock(Post.class);
		postDTO = mock(PostDTO.class);
	}

	@DisplayName("Test-Create-Post")
	@Test
	@Order(1)
	public void testCreatePost() throws Exception {
		// given - precondition or setup
		when(postDAO.insertUseDSL(post)).thenReturn(new Result<Post>(1, post));
		when(converter.convertToDomain(postDTO)).thenReturn(post);
		when(converter.convertToDTO(post)).thenReturn(postDTO);

		// then
		assertThat(postService.createPost(postDTO)).isNotNull();
		assertThat(postService.createPost(null)).isNull();
	}

	@DisplayName("Test-Update-Post")
	@Test
	@Order(2)
	public void testUpdatePost() {
		// given - precondition or setup
		when(postDAO.updateUseDSL(post)).thenReturn(new Result<Post>(1, post));
		when(converter.convertToDomain(postDTO)).thenReturn(post);
		when(converter.convertToDTO(post)).thenReturn(postDTO);
		when(postDTO.getId()).thenReturn(999);
		when(postDAO.count(postDTO.getId())).thenReturn(999L);

		// then
		assertEquals(postDTO.getId(), 999);
		assertEquals(postDAO.count(postDTO.getId()), 999L);
		assertThat(postService.updatePost(postDTO)).isNotNull();
	}

	@DisplayName("Test-Delete-Post")
	@Test
	@Order(3)
	public void testDeletePost() {
		// given - precondition or setup
		when(postDAO.deleteUseDSL(post)).thenReturn(new Result<Post>(1, post));
		when(converter.convertToDomain(postDTO)).thenReturn(post);
		when(converter.convertToDTO(post)).thenReturn(postDTO);
		when(postDTO.getId()).thenReturn(999);
		when(postDAO.count(postDTO.getId())).thenReturn(999L);

		// then
		assertEquals(postDTO.getId(), 999);
		assertEquals(postDAO.count(postDTO.getId()), 999L);
		assertThat(postService.deletePost(postDTO)).isNotNull();
	}

	@DisplayName("Test-Get-By-Id-Post")
	@Test
	@Order(4)
	public void testGetByIdPost() {
		// given - precondition or setup
		when(converter.convertToDTO(post)).thenReturn(postDTO);
		when(postDTO.getId()).thenReturn(1);
		when(postDAO.selectByIdUseDSL(postDTO.getId())).thenReturn(Optional.of(post));

		// then
		assertEquals(postDTO.getId(), 1);
		assertThat(postService.getById(postDTO.getId())).isNotNull();
	}

	@DisplayName("Test-Get-All-Post")
	@Test
	@Order(5)
	public void testGetAllPost() {
		// given - precondition or setup
		when(converter.convertToDTO(post)).thenReturn(postDTO);
		when(postDAO.findAllPostUseDSL()).thenReturn(Collections.singletonList(post));

		// then
		assertThat(postService.getAll()).isNotNull();
	}

	@DisplayName("Delete-All-Post")
	@Test
	@Order(6)
	public void testDeleteAllPost() {
		// given - precondition or setup
		when(postDAO.findAllPostUseDSL()).thenReturn(Collections.singletonList(post));
		when(postDAO.delete(post)).thenReturn(new Result<Post>(1, post));
		postService.deleteAllPost();

		// then
		verify(postDAO, times(1)).findAllPostUseDSL();
		verify(postDAO).delete(post);
	}

	@DisplayName("Exist-Post")
	@Test
	@Order(7)
	public void testExistPost() {
		// given - precondition or setup
		when(postDTO.getId()).thenReturn(1);
		when(postDAO.count(postDTO.getId())).thenReturn(1L);

		// then
		assertThat(postService.existPost(postDTO.getId())).isEqualTo(true);
	}

	@DisplayName("Save-All-Post")
	@Test
	@Order(8)
	public void testSaveAllPost() {
		// given - precondition or setup
		when(converter.convertToDomain(postDTO)).thenReturn(post);
		when(converter.convertToListDTO(Collections.singletonList(post)))
				.thenReturn(Collections.singletonList(postDTO));
		when(postDAO.insertAllUseDSL(Collections.singletonList(post)))
				.thenReturn(new BatchResult<Post>(new int[] { 1 }, Collections.singletonList(post)));

		// then
		assertThat(postService.saveAll(Collections.singletonList(postDTO))).isNotNull();
	}

	@DisplayName("Test-Update-Post-Fail")
	@Test
	@Order(9)
	public void testUpdatePostFail() {
		// given - precondition or setup
		when(postDTO.getId()).thenReturn(null);

		// then
		assertThat(postService.updatePost(postDTO)).isNull();
	}

	@DisplayName("Test-Delete-Post-Fail")
	@Test
	@Order(10)
	public void testDeletePostFail() {
		// given - precondition or setup
		when(postDTO.getId()).thenReturn(null);

		// then
		assertThat(postService.deletePost(postDTO)).isNull();
	}
	
	@DisplayName("Test-Get-By-Id-Post-Fail")
	@Test
	@Order(11)
	public void testGetByIdPostFail() {
		// given - precondition or setup
		when(postDTO.getId()).thenReturn(-1);
		when(postDAO.selectByIdUseDSL(postDTO.getId())).thenReturn(Optional.empty());

		// then
		assertThat(postService.getById(postDTO.getId())).isNull();
	}
	
	@DisplayName("Exist-Post-Fail")
	@Test
	@Order(12)
	public void testExistPostFail() {
		// given - precondition or setup
		when(postDTO.getId()).thenReturn(-1);
		when(postDAO.count(postDTO.getId())).thenReturn(-1L);

		// then
		assertThat(postService.existPost(postDTO.getId())).isEqualTo(false);
	}

}
