package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dao.IPostDAO;
import com.sysexevn.sunshinecity.domain.Post;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.service.IPostService;

@Service
public class PostService implements IPostService{

	@Autowired
	private IPostDAO postDAO;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public int createPost(PostDTO post) {
		
		return postDAO.insert(mapper.map(post, Post.class));
	}

	@Override
	public int updatePost(PostDTO post) {
		
		return postDAO.update(mapper.map(post, Post.class));
	}

	@Override
	public int deletePost(PostDTO post) {
		
		return postDAO.delete(mapper.map(post, Post.class));
	}

	@Override
	public int[] saveAll(List<PostDTO> posts) {
		
		List<Post> listPost = new ArrayList<>();
		posts.forEach(post -> {
			listPost.add(mapper.map(post, Post.class));
		});
		return postDAO.insertAll(listPost);
	}

	@Override
	public PostDTO getById(Integer id) {
		
		return mapper.map(postDAO.selectById(id), PostDTO.class);
	}

	@Override
	public List<PostDTO> getAll() {
		
		List<PostDTO> listPost = new ArrayList<>();
		postDAO.findAllPost().forEach(post -> {
			listPost.add(mapper.map(post, PostDTO.class));
		});
		return listPost;
	}

	@Override
	public void deleteAllPost() {
		
		postDAO.findAllPost().forEach(post -> {
			postDAO.delete(post);
		});
	}

	@Override
	public boolean existPost(Integer id) {
		
		if(postDAO.count(id) > 0)
			return true;
		return false;
	}
}
