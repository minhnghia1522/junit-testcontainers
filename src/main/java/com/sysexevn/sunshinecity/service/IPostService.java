package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.dto.PostDTO;

public interface IPostService {

	PostDTO createPost(PostDTO post);
	PostDTO updatePost(PostDTO post);
	PostDTO deletePost(PostDTO post);
	List<PostDTO> saveAll(List<PostDTO> posts);
	PostDTO getById(Integer id);
	List<PostDTO> getAll();
	void deleteAllPost();
	boolean existPost(Integer id);
}
