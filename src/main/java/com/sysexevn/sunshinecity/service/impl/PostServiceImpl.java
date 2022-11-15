package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.PostConverter;
import com.sysexevn.sunshinecity.dao.IPostDAO;
import com.sysexevn.sunshinecity.domain.Post;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDAO postDAO;

	@Autowired
	private PostConverter converter;

	@Override
	public PostDTO createPost(PostDTO post) {

		Post postInsert = converter.convertToDomain(post);
		Result<Post> resultInsert = postDAO.insertUseDSL(postInsert);
		post = converter.convertToDTO(resultInsert.getEntity());
		return post;
	}

	@Override
	public PostDTO updatePost(PostDTO post) {

		Post postUpdate = converter.convertToDomain(post);
		Result<Post> resultUpdate = postDAO.updateUseDSL(postUpdate);
		post = converter.convertToDTO(resultUpdate.getEntity());
		return post;
	}

	@Override
	public PostDTO deletePost(PostDTO post) {

		Post postDelete = converter.convertToDomain(post);
		Result<Post> resultDelete = postDAO.deleteUseDSL(postDelete);
		post = converter.convertToDTO(resultDelete.getEntity());
		return post;
	}

	@Override
	public List<PostDTO> saveAll(List<PostDTO> posts) {

		List<Post> listPost = new ArrayList<>();
		posts.forEach(p -> {
			Post post = converter.convertToDomain(p);
			listPost.add(post);
		});
		BatchResult<Post> resultSaveAll = postDAO.insertAllUseDSL(listPost);
		posts = converter.convertToListDTO(resultSaveAll.getEntities());
		return posts;
	}

	@Override
	public PostDTO getById(Integer id) {

		Optional<Post> post = postDAO.selectByIdUseDSL(id);
		if (post.isPresent()) {
			PostDTO dto = converter.convertToDTO(post.get());
			return dto;
		}
		return null;
	}

	@Override
	public List<PostDTO> getAll() {

		List<PostDTO> listPost = new ArrayList<>();
		postDAO.findAllPostUseDSL().forEach(post -> {
			PostDTO dto = converter.convertToDTO(post);
			listPost.add(dto);
		});
		return listPost;
	}

	@Override
	public void deleteAllPost() {

		postDAO.findAllPostUseDSL().forEach(post -> {
			postDAO.delete(post);
		});
	}

	@Override
	public boolean existPost(Integer id) {

		if (postDAO.count(id) > 0)
			return true;
		return false;
	}
}
