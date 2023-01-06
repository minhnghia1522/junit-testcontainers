package com.sysexevn.sunshinecity.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.service.IProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	@Autowired
	public IProductService service;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductDto p) {
		ProductDto result = service.save(p);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {
		ProductDto result = service.getById(id);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/get-list")
	public ResponseEntity<?> getList() {
		List<ProductDto> results = service.getList();
		return ResponseEntity.ok(results);
	}

	@PatchMapping
	public ResponseEntity<?> update(@RequestBody ProductDto p) {
		ProductDto result = service.update(p);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam int id) {
		ProductDto pDto = new ProductDto();
		pDto.setId(id);
		service.delete(pDto);
		return ResponseEntity.ok("Deleted");

	}

	@GetMapping("/export")
	public ResponseEntity<?> exportData() throws FileNotFoundException, IOException {
		return ResponseEntity.ok(service.exportData());
	}

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		List<ProductDto> students = service.readDataFromExcel(fileName);
		return ResponseEntity.ok(students);
	}
}
