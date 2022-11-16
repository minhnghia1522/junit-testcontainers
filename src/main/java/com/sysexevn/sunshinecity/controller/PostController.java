package com.sysexevn.sunshinecity.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.response.OutputResponse;
import com.sysexevn.sunshinecity.service.IPostService;
import com.sysexevn.sunshinecity.service.IUploadFileService;
import com.sysexevn.sunshinecity.service.impl.PostReadCSVService;
import com.sysexevn.sunshinecity.service.impl.PostReadExcelService;

@RestController
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private IUploadFileService uploadFileService;

	@PostMapping("/post")
	public ResponseEntity<OutputResponse<PostDTO>> insertPost(@RequestBody PostDTO dto) {
		PostDTO result = postService.createPost(dto);
		OutputResponse<PostDTO> out = new OutputResponse<>();
		out.setMessage("add post success!");
		out.setData(Collections.singletonList(result));
		return ResponseEntity.ok(out);
	}

	@PostMapping("/post/excel/upload")
	public ResponseEntity<OutputResponse<PostDTO>> insertPostFromExcelUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		
		long startTotal = System.currentTimeMillis();
		
		OutputResponse<PostDTO> out = new OutputResponse<>();
		// upload file
		if (file != null && !file.isEmpty()) {
			
			long startSaveFile = System.currentTimeMillis();
			String generatedFilename = uploadFileService.storeFile(file, "excel");
			long endSaveFile = System.currentTimeMillis();
			
			long startReadFile = System.currentTimeMillis();
			List<PostDTO> listSave =  PostReadExcelService.read("src/main/resources/static/" + generatedFilename);
			long endReadFile = System.currentTimeMillis();
			
			long startSaveDB = System.currentTimeMillis();
			List<PostDTO> result = postService
					.saveAll(listSave);
			long endSaveDB = System.currentTimeMillis();
			
			if (result.isEmpty())
				out.setMessage("can not save excel to database!");
			else
				out.setMessage("read excel success!");
			//out.setData(result);
			out.setMessage("upload file success!");
			// calculator time execute
			
			long endTotal = System.currentTimeMillis();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			System.out.println("*** Thời gian khi lưu file excel vào đĩa: " + formatter.format((endSaveFile - startSaveFile) / 1000d) + " seconds");
			System.out.println("*** Thời gian đọc file excel: " + formatter.format((endReadFile - startReadFile) / 1000d) + " seconds");
			System.out.println("*** Thời gian lưu dữ liệu vào database: " + formatter.format((endSaveDB - startSaveDB) / 1000d) + " seconds");
			System.out.println("*** Tổng thời gian thực thi của API: " + formatter.format((endTotal - startTotal) / 1000d) + " seconds");
			return ResponseEntity.ok(out);
		}
		out.setMessage("upload file fail!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
	}
	
	@PostMapping("/post/csv/upload")
	public ResponseEntity<OutputResponse<PostDTO>> insertPostFromCSVUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		
		long startTotal = System.currentTimeMillis();
		
		OutputResponse<PostDTO> out = new OutputResponse<>();
		// upload file
		if (file != null && !file.isEmpty()) {
			
			long startSaveFile = System.currentTimeMillis();
			String generatedFilename = uploadFileService.storeFile(file, "csv");
			long endSaveFile = System.currentTimeMillis();
			
			long startReadFile = System.currentTimeMillis();
			List<PostDTO> listSave =  PostReadCSVService.read("src/main/resources/static/" + generatedFilename);
			long endReadFile = System.currentTimeMillis();
			
			long startSaveDB = System.currentTimeMillis();
			List<PostDTO> result = postService
					.saveAll(listSave);
			long endSaveDB = System.currentTimeMillis();
			
			if (result.isEmpty())
				out.setMessage("can not save csv to database!");
			else
				out.setMessage("read csv success!");
			out.setMessage("upload file success!");
			
			// calculator time execute
			long endTotal = System.currentTimeMillis();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			System.out.println("*** Thời gian khi lưu file csv vào đĩa: " + formatter.format((endSaveFile - startSaveFile) / 1000d) + " seconds");
			System.out.println("*** Thời gian đọc file csv: " + formatter.format((endReadFile - startReadFile) / 1000d) + " seconds");
			System.out.println("*** Thời gian lưu dữ liệu vào database: " + formatter.format((endSaveDB - startSaveDB) / 1000d) + " seconds");
			System.out.println("*** Tổng thời gian thực thi của API: " + formatter.format((endTotal - startTotal) / 1000d) + " seconds");
			return ResponseEntity.ok(out);
		}
		out.setMessage("upload file fail!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
	}

	@DeleteMapping("/post/deleteAll")
	public ResponseEntity<OutputResponse<PostDTO>> deleteAllPost() {
		OutputResponse<PostDTO> out = new OutputResponse<>();
		postService.deleteAllPost();
		out.setMessage("delete all post success!");
		return ResponseEntity.ok(out);
	}

	@PutMapping("/post/{id}")
	public ResponseEntity<OutputResponse<PostDTO>> updatePost(@PathVariable("id") Integer id,
			@RequestBody PostDTO dto) {
		PostDTO dtoExist = postService.getById(id);
		OutputResponse<PostDTO> out = new OutputResponse<>();
		if (dtoExist != null) {
			dto.setId(id);
			PostDTO result = postService.updatePost(dto);
			out.setMessage("update post success!");
			out.setData(Collections.singletonList(result));
			return ResponseEntity.ok(out);
		}
		out.setMessage("post id not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(out);
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<OutputResponse<PostDTO>> deletePost(@PathVariable("id") Integer id) {
		PostDTO dto = postService.getById(id);
		OutputResponse<PostDTO> out = new OutputResponse<>();
		if (dto != null) {
			dto.setId(id);
			PostDTO result = postService.deletePost(dto);
			out.setMessage("delete post success!");
			out.setData(Collections.singletonList(result));
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
