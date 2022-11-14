package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.dto.PostDTO;

public interface IPostService {

	int createPost(PostDTO post);
	int updatePost(PostDTO post);
	int deletePost(PostDTO post);
	int[] saveAll(List<PostDTO> posts);
	PostDTO getById(Integer id);
	List<PostDTO> getAll();
	void deleteAllPost();
	boolean existPost(Integer id);
}
