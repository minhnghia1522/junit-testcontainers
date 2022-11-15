package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dao.IPostDAO;
import com.sysexevn.sunshinecity.domain.Post;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDAO postDAO;

//	@Autowired
//	private ModelMapper mapper;

	@Override
	public PostDTO createPost(PostDTO post) {

		Post postInsert = new Post();
		BeanUtils.copyProperties(post, postInsert);
		Result<Post> resultInsert = postDAO.insertUseDSL(postInsert);
		BeanUtils.copyProperties(resultInsert.getEntity(), post);
		return post;
	}

	@Override
	public PostDTO updatePost(PostDTO post) {

		Post postUpdate = new Post();
		BeanUtils.copyProperties(post, postUpdate);
		Result<Post> resultUpdate = postDAO.updateUseDSL(postUpdate);
		BeanUtils.copyProperties(resultUpdate.getEntity(), post);
		return post;
	}

	@Override
	public PostDTO deletePost(PostDTO post) {

		Post postDelete = new Post();
		BeanUtils.copyProperties(post, postDelete);
		Result<Post> resultDelete = postDAO.deleteUseDSL(postDelete);
		BeanUtils.copyProperties(resultDelete.getEntity(), post);
		return post;
	}

	@Override
	public List<PostDTO> saveAll(List<PostDTO> posts) {

		List<Post> listPost = new ArrayList<>();
		posts.forEach(p -> {
			Post post = new Post();
			BeanUtils.copyProperties(p, post);
			listPost.add(post);
		});
		BatchResult<Post> resultSaveAll = postDAO.insertAllUseDSL(listPost);
		BeanUtils.copyProperties(resultSaveAll.getEntities(), posts);
		return posts;
	}

	@Override
	public PostDTO getById(Integer id) {

		Optional<Post> post = postDAO.selectByIdUseDSL(id);
		if (post.isPresent()) {
			PostDTO dto = new PostDTO();
			BeanUtils.copyProperties(post.get(), dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<PostDTO> getAll() {

		List<PostDTO> listPost = new ArrayList<>();
		postDAO.findAllPostUseDSL().forEach(post -> {
			PostDTO dto = new PostDTO();
			BeanUtils.copyProperties(post, dto);
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
