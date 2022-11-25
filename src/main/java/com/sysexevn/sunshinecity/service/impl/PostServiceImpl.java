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
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.AuditingBaseEntity;
import com.sysexevn.sunshinecity.entity.Post;
import com.sysexevn.sunshinecity.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDAO postDAO;

	@Autowired
	private PostConverter converter;

	private PostDTO convertAuditing(PostDTO dto, AuditingBaseEntity auditing) {
		dto.setCreatedDate(auditing.getCreatedDate());
		dto.setCreatedBy(auditing.getCreatedBy());
		dto.setModifiedDate(auditing.getModifiedDate());
		dto.setModifiedBy(auditing.getModifiedBy());
		return dto;
	}
	
	@Override
	public PostDTO createPost(PostDTO post) {

		if (post != null) {
			Post postInsert = converter.convertToDomain(post);
			Result<Post> resultInsert = postDAO.insertUseDSL(postInsert);
			post = converter.convertToDTO(resultInsert.getEntity());
			post = convertAuditing(post, resultInsert.getEntity().getAuditing());
		}
		return post;
	}

	@Override
	public PostDTO updatePost(PostDTO post) {

		// test logic
		if(post.getId() == null || postDAO.count(post.getId()) <= 0) 
			return null;

		Optional<Post> postOld = postDAO.selectByIdUseDSL(post.getId());
		if(postOld.isPresent()) {
			// custom change
			if(post.getTitle() != null)
				postOld.get().setTitle(post.getTitle());
			if(post.getPostName() != null)
				postOld.get().setPostName(post.getPostName());
			if(post.getPostDescription() != null)
				postOld.get().setPostDescription(post.getPostDescription());
			// update
			Result<Post> resultUpdate = postDAO.updateUseDSL(postOld.get());
			post = converter.convertToDTO(resultUpdate.getEntity());
			post = convertAuditing(post, resultUpdate.getEntity().getAuditing());
		}
		return post;
	}

	@Override
	public PostDTO deletePost(PostDTO post) {

		// test logic
		if (post.getId() == null || postDAO.count(post.getId()) <= 0) {
			return null;
		}
		
		Post postDelete = converter.convertToDomain(post);
		Result<Post> resultDelete = postDAO.deleteUseDSL(postDelete);
		post = converter.convertToDTO(resultDelete.getEntity());
		post = convertAuditing(post, resultDelete.getEntity().getAuditing());
		return post;
	}

	@Override
	public List<PostDTO> saveAll(List<PostDTO> posts) {

		List<Post> listPost = new ArrayList<>();
		List<PostDTO> results = new ArrayList<>();
		posts.forEach(p -> {
			Post post = converter.convertToDomain(p);
			listPost.add(post);
		});
		BatchResult<Post> resultSaveAll = postDAO.insertAllUseDSL(listPost);
		resultSaveAll.getEntities().forEach(post -> {
			PostDTO dto = converter.convertToDTO(post);
			dto = convertAuditing(dto, post.getAuditing());
			results.add(dto);
		});
		// posts = converter.convertToListDTO(resultSaveAll.getEntities());
		return results;
	}

	@Override
	public PostDTO getById(Integer id) {

		Optional<Post> post = postDAO.selectByIdUseDSL(id);
		if (post.isPresent()) {
			PostDTO dto = converter.convertToDTO(post.get());
			dto = convertAuditing(dto, post.get().getAuditing());
			return dto;
		}
		return null;
	}

	@Override
	public List<PostDTO> getAll() {

		List<PostDTO> listPost = new ArrayList<>();
		postDAO.findAllPostUseDSL().forEach(post -> {
			PostDTO dto = converter.convertToDTO(post);
			dto = convertAuditing(dto, post.getAuditing());
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
