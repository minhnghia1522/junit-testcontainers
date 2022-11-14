package com.sysexevn.sunshinecity.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.response.OutputResponse;
import com.sysexevn.sunshinecity.service.IPostService;

@RestController
public class PostController {

	@Autowired
	private IPostService postService;

	@PostMapping("/post")
	public ResponseEntity<OutputResponse<Integer>> insertPost(@RequestBody PostDTO dto) {
		Integer id = postService.createPost(dto);
		OutputResponse<Integer> out = new OutputResponse<>();
		out.setMessage("add post success!");
		out.setData(Collections.singletonList(id));
		return ResponseEntity.ok(out);
	}

	@PutMapping("/post")
	public ResponseEntity<OutputResponse<Integer>> updatePost(@RequestBody PostDTO dto) {
		OutputResponse<Integer> out = new OutputResponse<>();
		if (postService.existPost(dto.getId())) {
			Integer id = postService.updatePost(dto);
			out.setMessage("update post success!");
			out.setData(Collections.singletonList(id));
			return ResponseEntity.ok(out);
		}
		out.setMessage("post id not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
	}

	@DeleteMapping("/post")
	public ResponseEntity<OutputResponse<Integer>> deletePost(@RequestBody PostDTO dto) {
		OutputResponse<Integer> out = new OutputResponse<>();
		if (postService.existPost(dto.getId())) {
			Integer id = postService.deletePost(dto);
			out.setMessage("delete post success!");
			out.setData(Collections.singletonList(id));
			return ResponseEntity.ok(out);
		}
		out.setMessage("post id nout found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
	}

	@GetMapping("/getAllPost")
	public ResponseEntity<OutputResponse<PostDTO>> getAllPost() {
		OutputResponse<PostDTO> out = new OutputResponse<PostDTO>();
		List<PostDTO> listResult = postService.getAll();
		if (listResult.isEmpty())
			out.setMessage("data is empty!");
		else
			out.setMessage("get all post success!");
		out.setData(listResult);
		return ResponseEntity.ok(out);
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<OutputResponse<PostDTO>> getPost(@PathVariable("id") Integer id) {
		OutputResponse<PostDTO> out = new OutputResponse<PostDTO>();
		try {
			out.setMessage("get post success!");
			out.setData(Collections.singletonList(postService.getById(id)));
			return ResponseEntity.ok(out);
		} catch (Exception e) {
			out.setMessage("get post not found!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(out);
		}
	}
}
